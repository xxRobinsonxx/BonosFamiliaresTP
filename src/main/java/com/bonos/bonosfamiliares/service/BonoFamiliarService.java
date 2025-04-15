package com.bonos.bonosfamiliares.service;

import com.bonos.bonosfamiliares.model.BonoFamiliar;
import com.bonos.bonosfamiliares.repository.BonoFamiliarRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BonoFamiliarService {

    private final BonoFamiliarRepository repository;

    public BonoFamiliarService(BonoFamiliarRepository repository) {
        this.repository = repository;
    }

    public BonoFamiliar registrarBonoFamiliar(BonoFamiliar bonoFamiliar) {
        return repository.save(bonoFamiliar);
    }

    public List<BonoFamiliar> listarBonosFamiliares() {
        return repository.findAll();
    }

    // Método para importar datos desde un archivo Excel (.xlsx)
    public List<BonoFamiliar> importarExcel(MultipartFile file) throws Exception {
        List<BonoFamiliar> bonos = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            // Asumimos que la fila 0 es la cabecera, los datos inician desde la fila 1
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                BonoFamiliar bono = new BonoFamiliar();
                bono.setFechaCorte(getStringCellValue(row.getCell(0)));
                bono.setEntidadTecnicaPromotor(getStringCellValue(row.getCell(1)));
                bono.setRuc(getStringCellValue(row.getCell(2)));
                bono.setPersonalidadJuridica(getStringCellValue(row.getCell(3)));
                bono.setNombreProyecto(getStringCellValue(row.getCell(4)));
                bono.setCodigoProyecto(getStringCellValue(row.getCell(5)));
                bono.setConvocatoria(getStringCellValue(row.getCell(6)));
                bono.setModalidad(getStringCellValue(row.getCell(7)));
                bono.setDepartamento(getStringCellValue(row.getCell(8)));
                bono.setProvincia(getStringCellValue(row.getCell(9)));
                bono.setDistrito(getStringCellValue(row.getCell(10)));
                bono.setUbigeo(getStringCellValue(row.getCell(11)));
                bono.setMontoBfho(getNumericCellValue(row.getCell(12)));
                bono.setValorObraVivienda(getNumericCellValue(row.getCell(13)));
                bono.setFechaDesembolso(getStringCellValue(row.getCell(14)));

                System.out.println("Fila " + i +
                        " => fechaCorte: " + bono.getFechaCorte() +
                        ", entidadTecnicaPromotor: " + bono.getEntidadTecnicaPromotor() +
                        ", ruc: " + bono.getRuc() +
                        ", personalidadJuridica: " + bono.getPersonalidadJuridica() +
                        ", nombreProyecto: " + bono.getNombreProyecto() +
                        ", codigoProyecto: " + bono.getCodigoProyecto() +
                        ", convocatoria: " + bono.getConvocatoria() +
                        ", modalidad: " + bono.getModalidad() +
                        ", departamento: " + bono.getDepartamento() +
                        ", provincia: " + bono.getProvincia() +
                        ", distrito: " + bono.getDistrito()  +
                        ", ubigeo: " + bono.getUbigeo() +
                        ", montoBfho: " + bono.getMontoBfho() +
                        ", valorObraVivienda: " + bono.getValorObraVivienda() +
                        ", fechaDesembolso: " + bono.getFechaDesembolso() );

                bonos.add(bono);
            }
        }
        return repository.saveAll(bonos);
    }

    // Método auxiliar para obtener valor String de una celda
    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                return sdf.format(cell.getDateCellValue());
            } else {
                // En caso de números enteros (por ejemplo, ubigeo)
                return String.valueOf((long) cell.getNumericCellValue());
            }
        }
        return null;
    }

    // Método auxiliar para obtener un valor numérico (Double) de una celda
    private Double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if(cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
