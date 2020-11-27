package br.unitins.petshop.view;

import java.time.LocalDateTime;
import java.util.Map;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleRenderingMode;
import br.unitins.petshop.model.Cliente;


public class CustomScheduleEvent implements ScheduleEvent {

    private String id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String styleClass;
    private Object data;
    private String url;
    private String description;
    private Cliente cliente;
    private boolean allDay;
    private boolean editable;

    public CustomScheduleEvent() {
    }

    public CustomScheduleEvent(String title, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.startDate = start;
        this.endDate = end;
        this.allDay = allDay;
        this.data = data;
    }

  
    public CustomScheduleEvent(Object data, String title, LocalDateTime startDate, LocalDateTime endDate, String description, Cliente cliente) {
    	this.data = data;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.cliente = cliente;
	}
    
	@Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String getGroupId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOverlapAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScheduleRenderingMode getRenderingMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getDynamicProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
}