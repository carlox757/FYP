package sample.model;

import javafx.beans.property.*;

import java.sql.Date;

/**
 * Created by Carlito on 12/04/2017.
 */

public class Artist {
	
	private IntegerProperty artistID;
	private StringProperty artistName;
	private StringProperty artistSurname;
	private StringProperty artistPhoneNumber;
	private StringProperty artistEmail;
	private StringProperty gender;
	private SimpleObjectProperty<Date> dateOfBirth;
	private StringProperty typeOfArtist;




	public Artist() {
		this.artistID = new SimpleIntegerProperty();
		this.artistName = new SimpleStringProperty();
		this.artistSurname = new SimpleStringProperty();
		this.artistPhoneNumber = new SimpleStringProperty();
		this.artistEmail = new SimpleStringProperty();
		this.gender = new SimpleStringProperty();
		this.dateOfBirth = new SimpleObjectProperty<>();
		this.typeOfArtist = new SimpleStringProperty();
	}


	
	public int getArtistID() {
		return artistID.get();
	}

	public void setArtistID(int artist_ID) {
		this.artistID.set(artist_ID);
	}

	public IntegerProperty artistIDProperty(){
		return artistID;
	}
	
	
	public String getArtistSurname() {
		return artistSurname.get();
	}

	public void setArtistSurname(String artistSurname) {
		this.artistSurname.set(artistSurname);
	}

	public StringProperty artistSurnameProperty(){
		return artistSurname;
	}


	public String getArtistPhoneNumber() {
		return artistPhoneNumber.get();
	}

	public void setArtistPhoneNumber(String artistPhoneNumber) {
		this.artistPhoneNumber.set(artistPhoneNumber);
	}

	public StringProperty artistPhoneNumberProperty(){
		return artistPhoneNumber;
	}



	public String getArtistEmail() {
		return artistEmail.get();
	}

	public void setArtistEmail(String artistEmail) {
		this.artistEmail.set(artistEmail);
	}

	public StringProperty artistEmailProperty(){
		return artistEmail;
	}


	
	
	public String getArtistName() {
		return artistName.get();
	}

	public void setArtistName(String artistName) {
		this.artistName.set(artistName);
	}

	public StringProperty artistNameProperty(){
		return artistName;
	}

	

	public Object getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth.set(dateOfBirth);
	}

	public SimpleObjectProperty<Date> artistDOBProperty(){
		return dateOfBirth;
	}

	
	
	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}

	public StringProperty artistGenderProperty(){
		return gender;
	}
	
	
	
	public String getTypeOfArtist() {
		return typeOfArtist.get();
	}

	public void setTypeOfArtist(String typeOfArtist) {
		this.typeOfArtist.set(typeOfArtist);
	}

	public StringProperty artistTypeProperty(){
		return typeOfArtist;
	}


}
