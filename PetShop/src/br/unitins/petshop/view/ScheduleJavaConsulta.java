package br.unitins.petshop.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.unitins.petshop.application.RepositoryException;
import br.unitins.petshop.application.Session;
import br.unitins.petshop.application.Util;
import br.unitins.petshop.controller.ClienteContoller;
import br.unitins.petshop.model.AgendamentoConsulta;
import br.unitins.petshop.model.Animal;
import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.model.Exame;
import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.repository.AgendaConsultaRepository;
import br.unitins.petshop.repository.ExameRepository;
import br.unitins.petshop.repository.FuncionarioRepository;

@Named
@ViewScoped
public class ScheduleJavaConsulta implements Serializable {
	private static final long serialVersionUID = -8825542416482334638L;
	
	private AgendamentoConsulta evento;

	private ScheduleModel eventModel;

	private ScheduleModel lazyEventModel;

	private List<AgendamentoConsulta>listaEventos;

	private AgendaConsultaRepository repo;

	private ScheduleEvent event;

	private boolean showWeekends = true;
	private boolean tooltip = true;
	private boolean allDaySlot = false;
	
	private String timeFormat;
    private String slotDuration="00:30:00";
    private String slotLabelInterval;
    private String scrollTime="06:00:00";
    private String minTime="04:00:00";
    private String maxTime="20:00:00";
    private String locale="en";
    private String timeZone="";
    private String clientTimeZone="local";
    private String columnHeaderFormat="";

	@PostConstruct
	public void init() {
		repo = new AgendaConsultaRepository();
		evento = new AgendamentoConsulta();
		eventModel = new DefaultScheduleModel();
		try {
			listaEventos = repo.findAll();
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro no SQL");
			e.printStackTrace();
		}
		for (AgendamentoConsulta ev : listaEventos) {
			DefaultScheduleEvent event = new DefaultScheduleEvent();
			
			event.setEndDate(ev.getData_fim());
			event.setStartDate(ev.getData_incio());
			event.setTitle(ev.getTitulo());
			event.setData(ev.getId());
			event.setDescription(ev.getDescricao());
			event.setEditable(true);
			event.setAllDay(false);
			if(ev.getStatus() == 0) {
				event.setStyleClass("emp1");
			}else {
				event.setStyleClass("emp2");
			}
			eventModel.addEvent(event);
		}  
	}

	public LocalDateTime getRandomDateTime(LocalDateTime base) {
		LocalDateTime dateTime = base.withMinute(0).withSecond(0).withNano(0);
		return dateTime.plusDays(((int) (Math.random()*30)));
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public AgendamentoConsulta getEvento() {
		return evento;
	}

	public void setEvento(AgendamentoConsulta evento) {
		this.evento = evento;
	}

	public List<AgendamentoConsulta> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<AgendamentoConsulta> listaEventos) {
		this.listaEventos = listaEventos;
	}

	private LocalDateTime previousDay8Pm() {
		return LocalDateTime.now().minusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime previousDay11Pm() {
		return LocalDateTime.now().minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime today1Pm() {
		return LocalDateTime.now().withHour(13).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime theDayAfter3Pm() {
		return LocalDateTime.now().plusDays(1).withHour(15).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime today6Pm() {
		return LocalDateTime.now().withHour(18).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime nextDay9Am() {
		return LocalDateTime.now().plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime nextDay11Am() {
		return LocalDateTime.now().plusDays(1).withHour(11).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime fourDaysLater3pm() {
		return LocalDateTime.now().plusDays(4).withHour(15).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime sevenDaysLater0am() {
		return LocalDateTime.now().plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime eightDaysLater0am() {
		return LocalDateTime.now().plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	public LocalDate getInitialDate() {
		return LocalDate.now().plusDays(1);
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	
	/*public void selecionaEvento(SelectEvent selectEvent) {
		ScheduleEvent event =(ScheduleEvent) selectEvent.getObject();
		for (AgendamentoConsulta ev : listaEventos) {
			if(ev.getId() == event.getData()) {
				evento = ev;
				break;
			}
		}
	}*/
	public void selecionaEvento(SelectEvent selectEvent) {
		ScheduleEvent event =(ScheduleEvent) selectEvent.getObject();

		for (AgendamentoConsulta ev : listaEventos) {
			if(ev.getId() == event.getData()) {
				evento = ev;
				break;
			}
		}
	}
	public void addEvent() {
		AgendaConsultaRepository repository = new AgendaConsultaRepository();
		if(evento.getId() == null) {
			if(evento.getData_incio().getDayOfMonth() <= evento.getData_fim().getDayOfMonth()) {
				try {
					repository.beginTransaction();
					repository.salvar(evento);
					repository.commitTransaction();
					init();
					Util.addInfoMessage("Operação realizada com sucesso.");
				} catch (RepositoryException e) {
					repository.rollbackTransaction();
					Util.addErrorMessage("Erro ao salvar agendamento entre em contato com adm");
					e.printStackTrace();
				}
			}else{
				Util.addErrorMessage("Dia inicial maior que final");
			}
		}else {
			try {
				repository.beginTransaction();
				repository.salvar(evento);
				repository.commitTransaction();
				init();
			}catch (Exception e) {
				repository.rollbackTransaction();
				Util.addErrorMessage("Erro ao salvar agendamento entre em contato com adm");
			}
		}
	}
	public List<Exame>completeServico(String query) {
		ExameRepository repo = new ExameRepository();
		try {
			return repo.findByNome(query, 6); 
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Exame>();
		}
	}
	
	public List<Funcionario> completeVeterinario(String query) {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			return repo.findVeterinario(query,6);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Funcionario>();
		}
	}
	
	/*public void remover() {
		AgendaServicoRepository repository = new AgendaServicoRepository();
		if(evento.getId() != null) {
			try {
				repository.beginTransaction();
				repository.remover(evento);
				repository.commitTransaction();
				init();
				Util.addInfoMessage("Remoção realizada com sucesso.");
			} catch (RepositoryException e) {
				repository.rollbackTransaction();
				Util.addErrorMessage("Erro ao remover agendamento entre em contato com adm");
				e.printStackTrace();
			}
		}else {
			Util.addErrorMessage("Não é possivel remover");
		}
	}*/
	
	public void retornarListaAnimais() {
		Cliente  cli = (Cliente) Session.getInstance().getAttribute("dadosCli");
		ClienteContoller controller= new ClienteContoller();
		controller.editar(cli);
	}
	public void retornarAnimal(Animal animal) {
		ClienteContoller controller = new ClienteContoller();
		controller.setAnimal(animal);
		evento.setAnimalAgenda(animal);
	}
	public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = DefaultScheduleEvent.builder().startDate((LocalDateTime) selectEvent.getObject()).endDate(((LocalDateTime) selectEvent.getObject()).plusMinutes(30)).build();
		Cliente  cli = (Cliente) Session.getInstance().getAttribute("dadosCli");
		try {
			if(cli.getNome() != null) {
				System.out.println(cli.getNome());
				evento = new AgendamentoConsulta();
				evento.setClienteAgenda(cli);
				evento.setData_incio(event.getStartDate());
				evento.setData_fim(event.getEndDate());
				Session.getInstance().setAttribute("dadosCli", new Cliente());
			}else {
				Util.addErrorMessage("Usuario não localizado para realizar o agendamento");
				evento = null;
			}
		}catch (NullPointerException e) {
			evento = null;
			Util.addErrorMessage("Usuario não localizado para realizar o agendamento");
		}
	}

	/*public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = DefaultScheduleEvent.builder().startDate((LocalDateTime) selectEvent.getObject()).endDate(((LocalDateTime) selectEvent.getObject()).plusMinutes(30)).build();
		evento = new AgendamentoServico();
		evento.setData_incio(event.getStartDate());
		evento.setData_fim(event.getEndDate());
	}*/

	public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
		event = selectEvent.getObject();
	}

	/*public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject().plusHours(1)).build();
    }*/

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento modificado:", "Total de dias:" + event.getDeltaAsDuration());
		for (AgendamentoConsulta ev : listaEventos) {
			if(ev.getId() == event.getScheduleEvent().getData()) {
				evento = ev;
				evento.setData_incio(event.getScheduleEvent().getStartDate());
				evento.setData_fim(event.getScheduleEvent().getEndDate());

				AgendaConsultaRepository repo= new AgendaConsultaRepository();
				try {
					repo.beginTransaction();
					repo.salvar(evento);
					repo.commitTransaction();
					init();
				} catch (RepositoryException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento modificado", "Dia inicial:" + event.getDeltaStartAsDuration() + ", Dia final: " + event.getDeltaEndAsDuration());
		for (AgendamentoConsulta ev : listaEventos) {
			if(ev.getId() == event.getScheduleEvent().getData()) {
				evento = ev;
				evento.setData_incio(event.getScheduleEvent().getStartDate());
				evento.setData_fim(event.getScheduleEvent().getEndDate());

				AgendaConsultaRepository repo= new AgendaConsultaRepository();
				try {
					repo.beginTransaction();
					repo.salvar(evento);
					repo.commitTransaction();
					init();
				} catch (RepositoryException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public boolean isShowWeekends() {
		return showWeekends;
	}

	public void setShowWeekends(boolean showWeekends) {
		this.showWeekends = showWeekends;
	}

	public boolean isTooltip() {
		return tooltip;
	}

	public void setTooltip(boolean tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isAllDaySlot() {
		return allDaySlot;
	}

	public void setAllDaySlot(boolean allDaySlot) {
		this.allDaySlot = allDaySlot;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getSlotDuration() {
		return slotDuration;
	}

	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}

	public String getSlotLabelInterval() {
		return slotLabelInterval;
	}

	public void setSlotLabelInterval(String slotLabelInterval) {
		this.slotLabelInterval = slotLabelInterval;
	}

	public String getScrollTime() {
		return scrollTime;
	}

	public void setScrollTime(String scrollTime) {
		this.scrollTime = scrollTime;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getClientTimeZone() {
		return clientTimeZone;
	}

	public void setClientTimeZone(String clientTimeZone) {
		this.clientTimeZone = clientTimeZone;
	}

	public String getColumnHeaderFormat() {
		return columnHeaderFormat;
	}

	public void setColumnHeaderFormat(String columnHeaderFormat) {
		this.columnHeaderFormat = columnHeaderFormat;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}
}
