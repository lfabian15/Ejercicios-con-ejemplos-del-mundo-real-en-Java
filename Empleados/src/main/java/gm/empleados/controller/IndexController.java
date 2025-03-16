package gm.empleados.controller;

import gm.empleados.modelo.Empleado;
import gm.empleados.service.imp.EmpleadoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    EmpleadoService empleadoService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String iniciar (ModelMap modelo){
        List<Empleado> empleados = empleadoService.listarEmpleados();
        empleados.forEach((empleado) -> logger.info(empleado.toString()));
        //Podemos compartir el modelo con la vista
        modelo.put("empleados", empleados);
        return "index"; //esto hace referencia al archivo index.jsp
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String mostrarAgregar(){
        return "agregar";//agregar.jsp
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String agregar(@ModelAttribute("empleadoForma") Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/";//redirige al path inicial
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String mostrarEditar(@RequestParam int idEmpleado, ModelMap modelo){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(idEmpleado);
        logger.info("Empleado a editar: " + empleado);
        modelo.put("empleado", empleado);
        return "editar";// Con eso va mostrar la pagina de editar.jsp
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(@ModelAttribute("empleadoForma") Empleado empleado){
        logger.info("Empleado a guardar (editar): " + empleado);
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/"; //Con esto redirigimoa al controlador a la ruta de inicio
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam int idEmpleado){
        logger.info("Empleado a eliminar: " + idEmpleado);
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleadoService.eliminarEmpleado(empleado);
        return "redirect:/";
    }
}
