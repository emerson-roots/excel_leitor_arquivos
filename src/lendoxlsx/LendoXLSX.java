/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendoxlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Br
 * 
 * necessário bibliotecas inclusas no projeto
 * 
 */
public class LendoXLSX {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// leituraLinhasExcel();
		leituraColunasExcel();

	}

	public static void leituraColunasExcel() {

		FileInputStream fisPlanilha = null;

		try {
			File file = new File("D:\\teste.xlsx");
			fisPlanilha = new FileInputStream(file);

			// cria um workbook = planilha toda com todas as abas
			XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

			// recuperamos apenas a primeira aba ou primeira planilha
			XSSFSheet sheet = workbook.getSheetAt(0);

			// retorna todas as linhas da planilha 0 (aba 1)
			Iterator<Row> rowIterator = sheet.iterator();

			long qtdLinhas = sheet.getLastRowNum();
			System.out.println("==========================Quantidade de linhas: " + qtdLinhas);

			List<String> nomeCliente = new ArrayList<String>();
			List<String> cpfOuCnpj = new ArrayList<String>();
			List<String> adm = new ArrayList<String>();
			List<String> notaFiscal = new ArrayList<String>();

			// varre todas as linhas da planilha 0
			while (rowIterator.hasNext()) {

				// recebe cada linha da planilha
				Row row = rowIterator.next();

				// pegamos todas as celulas desta linha
				Iterator<Cell> cellIterator = row.iterator();

				// varremos todas as celulas da linha atual
				while (cellIterator.hasNext()) {

					// criamos uma celula
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:// Cell.CELL_TYPE_STRING:
						System.out.println("coluna 0 " + cell.toString());
						nomeCliente.add(cell.toString());

						break;

					case 1:// Cell.CELL_TYPE_NUMERIC:
						System.out.println("coluna 1: " + cell.toString());
						cpfOuCnpj.add(cell.toString());
						break;

					case 2:// Cell.CELL_TYPE_FORMULA:
						System.out.println("coluna 2: " + cell.toString());
						adm.add(cell.toString());
						break;

					case 3:// Cell.CELL_TYPE_FORMULA:
						System.out.println("coluna 3: " + cell.toString());
						notaFiscal.add(cell.toString());
						break;
					}

				}
			}
			
			
			System.out.println("       ");
			System.out.println("       ");
			System.out.println("       ");
			System.out.println("##############################");
			System.out.println("       ");
			System.out.println("       ");
			System.out.println("       ");
			
			//recebe as listas criadas acima
			List<List<String>> dados = new ArrayList<>();
			dados = Arrays.asList(nomeCliente, cpfOuCnpj, adm, notaFiscal);

			//percorre colunas
			for (int coluna = 0; coluna < dados.size(); coluna++) {
				System.out.println("================= COLUNA: " + coluna + " - Nome Coluna: " + dados.get(coluna).get(0));
				
				//percorre linhas = inicia pelo 1 para pular coluna de cabeçalhos
				for (int linha = 1; linha < dados.get(coluna).size(); linha++) {
					System.out.println("LINHA " + linha + ": " + dados.get(coluna).get(linha));

				}
				
			}
			
			

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fisPlanilha.close();
			} catch (IOException ex) {
				Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	public static void leituraLinhasExcel() {

		FileInputStream fisPlanilha = null;

		try {
			File file = new File("D:\\teste.xlsx");
			fisPlanilha = new FileInputStream(file);

			// cria um workbook = planilha toda com todas as abas
			XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

			// recuperamos apenas a primeira aba ou primeira planilha
			XSSFSheet sheet = workbook.getSheetAt(0);

			// retorna todas as linhas da planilha 0 (aba 1)
			Iterator<Row> rowIterator = sheet.iterator();

			// varre todas as linhas da planilha 0
			while (rowIterator.hasNext()) {

				// recebe cada linha da planilha
				Row row = rowIterator.next();

				// pegamos todas as celulas desta linha
				Iterator<Cell> cellIterator = row.iterator();

				// varremos todas as celulas da linha atual
				while (cellIterator.hasNext()) {

					// criamos uma celula
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						System.out.println("TIPO STRING: " + cell.getStringCellValue());
						break;

					case Cell.CELL_TYPE_NUMERIC:
						System.out.println("TIPO NUMERICO: " + cell.getNumericCellValue());
						break;

					case Cell.CELL_TYPE_FORMULA:
						System.out.println("TIPO FORMULA: " + cell.getCellFormula());
					}

				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fisPlanilha.close();
			} catch (IOException ex) {
				Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
