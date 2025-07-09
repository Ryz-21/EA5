package LeoValdiviaSuasnabar.ExamenFinal.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import LeoValdiviaSuasnabar.ExamenFinal.Model.mascotas;
import LeoValdiviaSuasnabar.ExamenFinal.Services.mascotasServices;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/veterinaria")
public class MascotasController {

    @Autowired
    private mascotasServices mascotasService;

    // Listar mascotas
    @GetMapping
    public String listarMascotas(Model model) {
        List<mascotas> lista = mascotasService.listarMascotas();
        model.addAttribute("mascotas", lista);
        return "lista_mascotas"; // archivo: src/main/resources/templates/lista_mascotas.html
    }

    // Mostrar formulario para nueva mascota
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mascota", new mascotas());
        return "formulario_mascotas"; // archivo: src/main/resources/templates/formulario_mascotas.html
    }

    // Guardar o actualizar mascota
    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute mascotas mascota) {
        mascotasService.guardarMascota(mascota);
        return "redirect:/veterinaria";
    }

    // Editar mascota
    @GetMapping("/editar/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Optional<mascotas> mascota = mascotasService.obtenerMascostaPorId(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "formulario_mascotas";
        } else {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    }

    // Eliminar mascota
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotasService.elimarMascota(id);
        return "redirect:/veterinaria";
    }

    // Exportar a PDF
    @GetMapping("/exportar/pdf")
    public void exportarPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=mascotas.pdf");

        List<mascotas> lista = mascotasService.listarMascotas();

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Listado de Mascotas"));

        float[] columnWidths = {100F, 100F, 100F, 100F, 100F, 100F, 100F, 100F};
        Table table = new Table(columnWidths);
        table.addCell("Nombre");
        table.addCell("Tipo");
        table.addCell("Raza");
        table.addCell("Edad");
        table.addCell("Sexo");
        table.addCell("Dueño");
        table.addCell("Teléfono");
        table.addCell("Doctor");

        for (mascotas m : lista) {
            table.addCell(m.getNombre());
            table.addCell(m.getTipo());
            table.addCell(m.getRaza());
            table.addCell(m.getEdad());
            table.addCell(m.getSexo());
            table.addCell(m.getNombre_dueño());
            table.addCell(m.getTelefono_dueño());
            table.addCell(m.getNombreDoctorAsginado());
        }

        document.add(table);
        document.close();
    }

    // Exportar a Excel
    @GetMapping("/exportar/excel")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=mascotas.xlsx");

        List<mascotas> lista = mascotasService.listarMascotas();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Mascotas");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nombre");
        header.createCell(1).setCellValue("Tipo");
        header.createCell(2).setCellValue("Raza");
        header.createCell(3).setCellValue("Edad");
        header.createCell(4).setCellValue("Sexo");
        header.createCell(5).setCellValue("Nombre Dueño");
        header.createCell(6).setCellValue("Teléfono Dueño");
        header.createCell(7).setCellValue("Doctor Asignado");

        int rowIdx = 1;
        for (mascotas m : lista) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(m.getNombre());
            row.createCell(1).setCellValue(m.getTipo());
            row.createCell(2).setCellValue(m.getRaza());
            row.createCell(3).setCellValue(m.getEdad());
            row.createCell(4).setCellValue(m.getSexo());
            row.createCell(5).setCellValue(m.getNombre_dueño());
            row.createCell(6).setCellValue(m.getTelefono_dueño());
            row.createCell(7).setCellValue(m.getNombreDoctorAsginado());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
