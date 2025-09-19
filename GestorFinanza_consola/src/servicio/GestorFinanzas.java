package servicio;

import modelo.Usuario;
import modelo.Transaccion;
import modelo.MetaAhorro;
import modelo.Presupuesto;
import util.FileUtils;

import java.io.File;
import java.util.*;
import java.time.LocalDateTime;

public class GestorFinanzas {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private Map<String, MetaAhorro> metas = new HashMap<>();
    private List<Presupuesto> presupuestos = new ArrayList<>();
    private Map<String, Map<String, Double>> saldosCategorias = new HashMap<>();

    private final String pathSaldos = "data/saldos.csv";
    private int nextId = 1;

    private final String pathUsers = "data/usuarios.txt";
    private final String pathTrans = "data/transacciones.csv";
    private final String pathMetas = "data/metas.txt";
    private final String pathPres = "data/presupuestos.csv";

    public GestorFinanzas() {
        // ensure data folder exists
        FileUtils.ensureDataFolder();
    }
    public void cargarDatos() {
        try {
            List<String> us = FileUtils.readLines(pathUsers);
            for (String l : us) {
                if (l.trim().isEmpty()) continue;
                String[] p = l.split("\\|",4);
                if (p.length<4) continue;
                Usuario u = new Usuario(p[0], p[1], p[2], Integer.parseInt(p[3]));
                usuarios.put(u.getNombre(), u);
            }
            List<String> tr = FileUtils.readLines(pathTrans);
            for (String l : tr) {
                if (l.trim().isEmpty()) continue;
                Transaccion t = Transaccion.fromCSV(l);
                if (t!=null) {
                    transacciones.add(t);
                    if (t.getId()>=nextId) nextId = t.getId()+1;
                }
            }
            List<String> me = FileUtils.readLines(pathMetas);
            for (String l: me) {
                if (l.trim().isEmpty()) continue;
                MetaAhorro m = MetaAhorro.fromLine(l);
                if (m!=null) metas.put(m.getUsuario(), m);
            }
            List<String> pr = FileUtils.readLines(pathPres);
            for (String l: pr) {
                if (l.trim().isEmpty()) continue;
                Presupuesto p = Presupuesto.fromCSV(l);
                if (p!=null) presupuestos.add(p);
            }
            List<String> sd = FileUtils.readLines(pathSaldos);
            for (String l : sd) {
                if (l.trim().isEmpty()) continue;
                String[] p = l.split(",");
                if (p.length < 3) continue;
                String user = p[0];
                String cat = p[1];
                double val = Double.parseDouble(p[2]);
                if (!saldosCategorias.containsKey(user)) {
                    saldosCategorias.put(user, new HashMap<>());
                }
                saldosCategorias.get(user).put(cat, val);
            }
        } catch (Exception e) {
            System.out.println("Error cargando datos: " + e.getMessage());
        }

    }
    public void guardarDatos() {
        try {
            List<String> us = new ArrayList<>();
            for (Usuario u : usuarios.values()) us.add(u.toString());
            FileUtils.writeLines(pathUsers, us);

            List<String> tr = new ArrayList<>();
            for (Transaccion t : transacciones) tr.add(t.toCSV());
            FileUtils.writeLines(pathTrans, tr);

            List<String> me = new ArrayList<>();
            for (MetaAhorro m : metas.values()) me.add(m.toLine());
            FileUtils.writeLines(pathMetas, me);

            List<String> pr = new ArrayList<>();
            for (Presupuesto p : presupuestos) pr.add(p.toCSV());
            FileUtils.writeLines(pathPres, pr);
            List<String> sd = new ArrayList<>();
            for (String user : saldosCategorias.keySet()) {
                for (Map.Entry<String, Double> e : saldosCategorias.get(user).entrySet()) {
                    sd.add(user + "," + e.getKey() + "," + e.getValue());
                }
            }
            FileUtils.writeLines(pathSaldos, sd);
        } catch (Exception e) {
            System.out.println("Error guardando datos: " + e.getMessage());
        }

    }
    public boolean registrarUsuario(String nombre, String contrasena, String carrera, int edad) {
        if (usuarios.containsKey(nombre)) return false;
        Usuario u = new Usuario(nombre, contrasena, carrera, edad);
        usuarios.put(nombre, u);

// inicializar saldos por categoría
        Map<String, Double> catSaldo = new HashMap<>();
        List<String> cats = Arrays.asList("Comida","Transporte","Estudios","Ocio","Caprichos","Inversion","Ahorro","Otros");
        for (String c : cats) {
            catSaldo.put(c, 0.0);
        }
        saldosCategorias.put(nombre, catSaldo);

        guardarDatos();
        return true;

    }
    public Usuario login(String usuario, String contrasena) {
        Usuario u = usuarios.get(usuario);
        if (u==null) return null;
        if (!u.getContrasena().equals(contrasena)) return null;
        return u;
    }
    public void menuUsuario(Usuario u, Scanner sc) {
        while (true) {
            System.out.println("\n--- Menú Usuario ---");
            System.out.println("1) Registrar ingreso");
            System.out.println("2) Registrar gasto");
            System.out.println("3) Reporte detallado");
            System.out.println("4) Metas de ahorro");
            System.out.println("5) Gestión de presupuestos y asignaciones");
            System.out.println("6) Resetear todos los datos (comenzar de nuevo)");
            System.out.println("7) Cerrar sesión");
            System.out.print("Opción: ");
            String opt = sc.nextLine().trim();

            if (opt.equals("1")) registrarMovimiento(u, sc, "INGRESO");
            else if (opt.equals("2")) registrarMovimiento(u, sc, "GASTO");
            else if (opt.equals("3")) generarReporteUsuario(u);
            else if (opt.equals("4")) menuMetas(u, sc);
            else if (opt.equals("5")) menuGestionPresupuestos(u, sc);
            else if (opt.equals("6")) resetearDatos();
            else if (opt.equals("7")) break;
            else System.out.println("Inválido.");
        }
    }
    private void registrarMovimiento(Usuario u, Scanner sc, String tipo) {
        try {
            System.out.print("Monto: ");
            double monto = Double.parseDouble(sc.nextLine().trim());
            if (monto <= 0) {
                System.out.println("Monto inválido. Debe ser mayor que 0.");
                return;
            }

            List<String> cats = Arrays.asList("Comida","Transporte","Estudios","Ocio","Caprichos","Inversion","Ahorro","Otros");

            if (tipo.equals("GASTO")) {
                Map<String, Double> catSaldo = saldosCategorias.get(u.getNombre());
                if (catSaldo == null) {
                    catSaldo = new HashMap<>();
                    for (String c : cats) catSaldo.put(c, 0.0);
                    saldosCategorias.put(u.getNombre(), catSaldo);
                }

                System.out.println("Categorías disponibles: " + catSaldo.keySet());
                System.out.print("Categoría: ");
                String cat = sc.nextLine().trim();
                if (!cat.isEmpty()) cat = cat.substring(0,1).toUpperCase() + cat.substring(1).toLowerCase();

                if (!cats.contains(cat)) {
                    System.out.println("Categoría inválida.");
                    return;
                }

                double saldoActual = catSaldo.getOrDefault(cat, 0.0);

                // Buscar presupuesto si existe
                Presupuesto presupuesto = null;
                for (Presupuesto p : presupuestos) {
                    if (p.getUsuario().equals(u.getNombre()) && p.getCategoria().equals(cat)) {
                        presupuesto = p;
                        break;
                    }
                }
                double gastadoActual = (presupuesto != null) ? presupuesto.getGastado() : 0.0;

                boolean excedeAsignado = !cat.equals("Ahorro") && monto > saldoActual;
                boolean excedePresupuesto = !cat.equals("Ahorro") &&
                        (presupuesto != null && presupuesto.getLimite() > 0 && (gastadoActual + monto) > presupuesto.getLimite());

                // Advertencia saldo asignado
                if (excedeAsignado) {
                    System.out.println("⚠ ADVERTENCIA: Vas a exceder el saldo asignado en " + cat +
                            " (saldo actual: " + saldoActual + "). ¿Continuar? [s/n]");
                    String r = sc.nextLine().trim();
                    if (!r.equalsIgnoreCase("s")) {
                        System.out.println("Operación cancelada.");
                        return;
                    }
                }

                // Advertencia límite presupuesto
                if (excedePresupuesto) {
                    System.out.println("⚠ ADVERTENCIA: Vas a exceder el límite de presupuesto en " + cat +
                            " (límite: " + presupuesto.getLimite() +
                            ", ya gastado: " + gastadoActual + "). ¿Continuar? [s/n]");
                    String r2 = sc.nextLine().trim();
                    if (!r2.equalsIgnoreCase("s")) {
                        System.out.println("Operación cancelada.");
                        return;
                    }
                }

                // Crear transacción
                System.out.print("Descripción: ");
                String desc = sc.nextLine().trim();

                Transaccion t = new Transaccion(nextId++, u.getNombre(), LocalDateTime.now(), monto, cat, desc, tipo);
                transacciones.add(t);

                if (cat.equals("Ahorro")) {
                    // En ahorro el saldo sube, pero se descuenta del saldo general
                    catSaldo.put(cat, saldoActual + monto);
                    u.setSaldoGeneral(u.getSaldoGeneral() - monto);
                    if (u.getSaldoGeneral() < 0) u.setSaldoGeneral(0);

                    MetaAhorro m = metas.get(u.getNombre());
                    if (m != null) {
                        m.setMontoActual(m.getMontoActual() + monto);
                    }
                } else {
                    // En otras categorías el saldo baja
                    catSaldo.put(cat, saldoActual - monto);
                    if (presupuesto != null) {
                        presupuesto.setGastado(presupuesto.getGastado() + monto);
                    }
                }

                guardarDatos();
                System.out.println("Gasto registrado. Nuevo saldo en " + cat + ": " + catSaldo.get(cat));

            } else if (tipo.equals("INGRESO")) {
                System.out.print("Descripción: ");
                String desc = sc.nextLine().trim();

                Transaccion t = new Transaccion(nextId++, u.getNombre(), LocalDateTime.now(), monto, "", desc, tipo);
                transacciones.add(t);

                double nuevoSaldo = u.getSaldoGeneral() + monto;
                u.setSaldoGeneral(nuevoSaldo);

                guardarDatos();
                System.out.println("Ingreso agregado al saldo general. Nuevo saldo general: " + nuevoSaldo);
            }

        } catch (Exception e) {
            System.out.println("Error registrando movimiento: " + e.getMessage());
        }
    }
    private void listarTransaccionesUsuario(Usuario u) {
        System.out.println("--- Transacciones de " + u.getNombre() + " ---");
        for (Transaccion t : transacciones) {
            if (t.getUsuario().equals(u.getNombre())) {
                System.out.println(t.toCSV());
            }
        }
    }
    private void menuMetas(Usuario u, Scanner sc) {
        while (true) {
            System.out.println("\nMetas: 1) Ver meta  2) Crear/Actualizar meta  3) Volver");
            System.out.print("Opción: "); String o = sc.nextLine().trim();
            if (o.equals("1")) {
                MetaAhorro m = metas.get(u.getNombre());
                if (m==null) System.out.println("No tienes meta establecida.");
                else {
                    double p = (m.getMontoActual() / m.getMontoMeta()) * 100;
                    System.out.println("Meta: " + m.getMontoMeta() + " | Ahorrado: " + m.getMontoActual() + " | " + String.format("%.2f", p) + "%");
                }
            } else if (o.equals("2")) {
                System.out.print("Monto meta: "); double mm = Double.parseDouble(sc.nextLine().trim());
                MetaAhorro m = metas.get(u.getNombre());
                if (m==null) m = new MetaAhorro(u.getNombre(), mm, 0);
                else m = new MetaAhorro(u.getNombre(), mm, m.getMontoActual());
                metas.put(u.getNombre(), m);
                guardarDatos();
                System.out.println("Meta guardada.");
            } else if (o.equals("3")) break;
            else System.out.println("Inválido.");
        }
    }
    private void generarReporteUsuario(Usuario u) {
        double ingresosTotales = 0, gastosTotales = 0;
        Map<String, Double> gastoPorCat = new HashMap<>();

        for (Transaccion t : transacciones) {
            if (!t.getUsuario().equals(u.getNombre())) continue;
            if ("INGRESO".equals(t.getTipo())) ingresosTotales += t.getMonto();
            else if ("GASTO".equals(t.getTipo())) {
                gastosTotales += t.getMonto();
                gastoPorCat.put(t.getCategoria(), gastoPorCat.getOrDefault(t.getCategoria(), 0.0) + t.getMonto());
            }
        }

        System.out.println("\n--- Reporte detallado de " + u.getNombre() + " ---");
        System.out.println("Ingresos totales: " + ingresosTotales);
        System.out.println("Gastos totales: " + gastosTotales);
        System.out.println("Saldo general disponible: " + u.getSaldoGeneral());

        List<String> cats = Arrays.asList("Comida","Transporte","Estudios","Ocio","Caprichos","Inversion","Ahorro","Otros");
        Map<String, Double> catSaldo = saldosCategorias.getOrDefault(u.getNombre(), new HashMap<>());

        for (String c : cats) {
            if (c.equals("Ahorro")) {
                // Reporte especial para ahorro
                double ahorrado = catSaldo.getOrDefault(c, 0.0);
                MetaAhorro m = metas.get(u.getNombre());
                String progreso = "";
                if (m != null && m.getMontoMeta() > 0) {
                    double porc = (m.getMontoActual() / m.getMontoMeta()) * 100;
                    progreso = " | Progreso meta: " + String.format("%.2f", porc) + "%";
                }
                System.out.println("\nCategoría: Ahorro");
                System.out.println("  - Ahorrado: " + ahorrado + progreso);

                // Mostrar transacciones de ahorro
                for (Transaccion t : transacciones) {
                    if (t.getUsuario().equals(u.getNombre()) && "Ahorro".equals(t.getCategoria())) {
                        System.out.println("    • " + t.getTipo() + ": " + t.getMonto() + " (" + t.getDescripcion() + ")");
                    }
                }
            } else {
                double saldoActual = catSaldo.getOrDefault(c, 0.0);
                double gastado = gastoPorCat.getOrDefault(c, 0.0);
                double asignadoTotal = saldoActual + gastado;

                double limite = 0;
                for (Presupuesto p : presupuestos) {
                    if (p.getUsuario().equals(u.getNombre()) && p.getCategoria().equals(c)) {
                        limite = p.getLimite();
                        break;
                    }
                }

                String adv = "";
                if (saldoActual < 0) adv += " ⚠ saldo negativo";
                if (limite > 0 && gastado > limite) adv += " ⚠ excediste presupuesto";

                System.out.println("\nCategoría: " + c);
                System.out.println("  - Asignado: " + asignadoTotal);
                System.out.println("  - Gastado: " + gastado);
                System.out.println("  - Saldo actual: " + saldoActual + adv);

                // Mostrar transacciones normales
                for (Transaccion t : transacciones) {
                    if (t.getUsuario().equals(u.getNombre()) && c.equals(t.getCategoria())) {
                        System.out.println("    • " + t.getTipo() + ": " + t.getMonto() + " (" + t.getDescripcion() + ")");
                    }
                }
            }
        }

        // Mostrar transacciones de ingresos también
        System.out.println("\n--- Detalle de ingresos ---");
        for (Transaccion t : transacciones) {
            if (t.getUsuario().equals(u.getNombre()) && "INGRESO".equals(t.getTipo())) {
                System.out.println("  • Ingreso: " + t.getMonto() + " (" + t.getDescripcion() + ")");
            }
        }

        // Guardar reporte en archivo
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Reporte para: " + u.getNombre());
            lines.add("Ingresos totales: " + ingresosTotales);
            lines.add("Gastos totales: " + gastosTotales);
            lines.add("Saldo general disponible: " + u.getSaldoGeneral());
            lines.add("--- Detalle por categoría ---");
            for (String c : cats) {
                if (c.equals("Ahorro")) {
                    double ahorrado = catSaldo.getOrDefault(c, 0.0);
                    MetaAhorro m = metas.get(u.getNombre());
                    String progreso = "";
                    if (m != null && m.getMontoMeta() > 0) {
                        double porc = (m.getMontoActual() / m.getMontoMeta()) * 100;
                        progreso = " | Progreso meta: " + String.format("%.2f", porc) + "%";
                    }
                    lines.add("Ahorro -> Ahorrado: " + ahorrado + progreso);
                } else {
                    double saldoActual = catSaldo.getOrDefault(c, 0.0);
                    double gastado = gastoPorCat.getOrDefault(c, 0.0);
                    double asignadoTotal = saldoActual + gastado;
                    double limite = 0;
                    for (Presupuesto p : presupuestos) {
                        if (p.getUsuario().equals(u.getNombre()) && p.getCategoria().equals(c)) {
                            limite = p.getLimite();
                            break;
                        }
                    }
                    String linea = c + " -> Asignado: " + asignadoTotal + " | Gastado: " + gastado +
                            " | Saldo actual: " + saldoActual;
                    if (saldoActual < 0) linea += " ⚠ saldo negativo";
                    if (limite > 0 && gastado > limite) linea += " ⚠ excediste presupuesto";
                    lines.add(linea);
                }
            }
            lines.add("--- Detalle de ingresos ---");
            for (Transaccion t : transacciones) {
                if (t.getUsuario().equals(u.getNombre()) && "INGRESO".equals(t.getTipo())) {
                    lines.add("  • Ingreso: " + t.getMonto() + " (" + t.getDescripcion() + ")");
                }
            }
            FileUtils.writeLines("data/report_" + u.getNombre() + ".txt", lines);
            System.out.println("✅ Reporte exportado a data/report_" + u.getNombre() + ".txt");
        } catch (Exception e) {
            System.out.println("Error exportando reporte: " + e.getMessage());
        }
    }
    private void asignarPresupuesto(Usuario u, Scanner sc) {
        List<String> cats = Arrays.asList("Comida","Transporte","Estudios","Ocio","Caprichos","Inversion","Ahorro","Otros");
        Map<String, Double> catSaldo = saldosCategorias.get(u.getNombre());
        if (catSaldo == null) {
            catSaldo = new HashMap<>();
            for (String c : cats) catSaldo.put(c, 0.0);
            saldosCategorias.put(u.getNombre(), catSaldo);
        }

        System.out.println("Saldo general disponible: " + u.getSaldoGeneral());
        System.out.println("Categorías disponibles: " + catSaldo.keySet());
        System.out.print("Categoría: ");
        String cat = sc.nextLine().trim();
        if (!cat.isEmpty()) cat = cat.substring(0,1).toUpperCase() + cat.substring(1).toLowerCase();

        if (!catSaldo.containsKey(cat)) {
            System.out.println("Categoría inválida.");
            return;
        }

        System.out.print("Monto a asignar: ");
        double monto = Double.parseDouble(sc.nextLine().trim());
        if (monto > u.getSaldoGeneral()) {
            System.out.println("No tienes suficiente saldo general.");
            return;
        }

        u.setSaldoGeneral(u.getSaldoGeneral() - monto);
        catSaldo.put(cat, catSaldo.get(cat) + monto);
        guardarDatos();
        System.out.println("Asignado " + monto + " a " + cat + ". Saldo general ahora: " + u.getSaldoGeneral());
    }
    private void reiniciarBaseDeDatos() {
        try {
            new File("data/usuarios.txt").delete();
            new File("data/transacciones.csv").delete();
            new File("data/presupuestos.csv").delete();
            new File("data/metas.csv").delete();
            new File("data/saldos.csv").delete();

            usuarios.clear();
            transacciones.clear();
            presupuestos.clear();
            metas.clear();
            saldosCategorias.clear();

            System.out.println("✅ Base de datos reiniciada. Ahora todo está en blanco.");
        } catch (Exception e) {
            System.out.println("Error reiniciando: " + e.getMessage());
        }
    }
    private void menuGestionPresupuestos(Usuario u, Scanner sc) {
        while (true) {
            System.out.println("\n--- Gestión de presupuestos ---");
            System.out.println("1) Asignar saldo a categoría");
            System.out.println("2) Establecer límite de gasto");
            System.out.println("3) Ver límites y asignaciones actuales");
            System.out.println("4) Volver");
            System.out.print("Opción: ");
            String o = sc.nextLine().trim();

            if (o.equals("1")) asignarPresupuesto(u, sc);
            else if (o.equals("2")) establecerLimite(u, sc);
            else if (o.equals("3")) verAsignacionesYLímites(u);
            else if (o.equals("4")) break;
            else System.out.println("Inválido.");
        }
    }
    private void establecerLimite(Usuario u, Scanner sc) {
        List<String> cats = Arrays.asList("Comida","Transporte","Estudios","Ocio","Caprichos","Inversion","Ahorro","Otros");
        Map<String, Double> catSaldo = saldosCategorias.get(u.getNombre());
        if (catSaldo == null) {
            catSaldo = new HashMap<>();
            for (String c : cats) catSaldo.put(c, 0.0);
            saldosCategorias.put(u.getNombre(), catSaldo);
        }

        System.out.println("Categorías disponibles: " + catSaldo.keySet());
        System.out.print("Categoría: ");
        String cat = sc.nextLine().trim();
        if (!cat.isEmpty()) cat = cat.substring(0,1).toUpperCase() + cat.substring(1).toLowerCase();
        if (!catSaldo.containsKey(cat)) {
            System.out.println("Categoría inválida.");
            return;
        }

        System.out.print("Límite máximo de gasto: ");
        double limite = Double.parseDouble(sc.nextLine().trim());

        boolean found = false;
        for (Presupuesto p : presupuestos) {
            if (p.getUsuario().equals(u.getNombre()) && p.getCategoria().equals(cat)) {
                p.setLimite(limite);
                found = true;
                break;
            }
        }
        if (!found) {
            presupuestos.add(new Presupuesto(u.getNombre(), cat, limite, 0));
        }

        guardarDatos();
        System.out.println("✅ Límite establecido para " + cat + ": " + limite);
    }
    private void verAsignacionesYLímites(Usuario u) {
        System.out.println("--- Asignaciones y límites de " + u.getNombre() + " ---");
        Map<String, Double> catSaldo = saldosCategorias.get(u.getNombre());
        if (catSaldo == null) {
            System.out.println("Aún no tienes asignaciones. Usa 'Asignar saldo a categoría'.");
            return;
        }
        for (String c : catSaldo.keySet()) {
            double asignado = catSaldo.get(c);
            double limite = 0;
            for (Presupuesto p : presupuestos) {
                if (p.getUsuario().equals(u.getNombre()) && p.getCategoria().equals(c)) {
                    limite = p.getLimite();
                    break;
                }
            }
            System.out.println(c + " -> Asignado: " + asignado + " | Límite: " + (limite == 0 ? "No establecido" : limite));
        }
    }
    private void resetearDatos() {
        // 🔹 Mantener usuarios
        // No hacemos usuarios.clear();
        for (Usuario u : usuarios.values()) {
            u.setSaldoGeneral(0);
        }
        // 🔹 Borrar todo lo demas
        transacciones.clear();
        presupuestos.clear();
        saldosCategorias.clear();
        metas.clear();

        try {
            // Guardar los cambios en archivos vacíos
            FileUtils.writeLines(pathTrans, new ArrayList<>()); // transacciones vacías
            FileUtils.writeLines(pathPres, new ArrayList<>());  // presupuestos vacíos
            FileUtils.writeLines(pathMetas, new ArrayList<>()); // metas vacías
            FileUtils.writeLines(pathSaldos, new ArrayList<>()); // saldos vacíos
        } catch (Exception e) {
            System.out.println("Error reseteando archivos: " + e.getMessage());
        }

        System.out.println("✅ Todos los datos (menos usuarios) fueron eliminados.");
    }
}
