package br.unitins.vendas.report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.petshop.application.JDBCUtil;

@WebServlet("/agendamentoservlet")
public class AgendamentoReportServlet extends ReportServlet {
	
	private static final long serialVersionUID = -2920292417348575303L;

	@Override
	public String getArquivoJasper() {
		return "agendamentos.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		 return null;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();
	}

}
