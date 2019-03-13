package br.com.workspace.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Sets the workspace table
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Workspace.GET_BY_PERSON, query = "select a from Workspace a where a.person.id = :idPerson") })
public class Workspace implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	public static final String GET_BY_PERSON = "Workspace.getByPerson";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Person person;

	private String state;

	private String city;

	private String neighborhood;

	private String address;

	private String tableAmmount;

	private String chairAmmount;

	private String costPerHour;

	private String avaliablePeriods;

	private String avaliableHours;

	private Integer description;

	public Workspace() {
	}

	public Workspace(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public void setChairAmmount(String chairAmmount) {
		this.chairAmmount = chairAmmount;
	}

	public String getChairAmmount() {
		return chairAmmount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTableAmmount() {
		return tableAmmount;
	}

	public void setTableAmmount(String tableAmmount) {
		this.tableAmmount = tableAmmount;
	}

	public String getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(String costPerHour) {
		this.costPerHour = costPerHour;
	}

	public String getAvaliablePeriods() {
		return avaliablePeriods;
	}

	public void setAvaliablePeriods(String avaliablePeriods) {
		this.avaliablePeriods = avaliablePeriods;
	}

	public String getAvaliableHours() {
		return avaliableHours;
	}

	public void setAvaliableHours(String avaliableHours) {
		this.avaliableHours = avaliableHours;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	public Integer getDescription() {
		return description;
	}
}
