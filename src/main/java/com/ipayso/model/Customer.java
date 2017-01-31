package com.ipayso.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", nullable = false, updatable = false)
	private long id;
	
	@Column(name = "customer_nickname", nullable = false, length = 20)
	private String nickname;
	
	@Column(name = "customer_OAuth", length = 20)
	private String OAuth;
	
	@Column(name = "customer_forename", nullable = false, length = 20)
	private String forename;
	
	@Column(name = "customer_lastname", nullable = false, length = 20)
	private String lastname;
	
	@Column(name = "customer_bankAccountIban", length = 20)
	private String bankAccountIban;
	
	@Column(name = "customer_bankAccountBICC", length = 20)
	private String bankAccountBICC;
	
	@Column(name = "customer_eMail", nullable = false, length = 20)
	private String eMail;
	
	@Column(name = "customer_pinCodeHash", length = 20)
	private String pinCodeHash;
	
	@Column(name = "customer_bankValidationHash", length = 20)
	private String bankValidationHash;
	
	@Column(name = "customer_pwHash", length = 20)
	private String pwHash;
	
	@Column(name = "customer_pwHintQuestion1", length = 20)
	private String pwHintQuestion1;
	
	@Column(name = "customer_pwHintAnswer1", length = 20)
	private String pwHintAnswer1;
	
	@Column(name = "customer_pwHintQuestion2", length = 20)
	private String pwHintQuestion2;
	
	@Column(name = "customer_pwHintAnswer2", length = 20)
	private String pwHintAnswer2;
	
	@Column(name = "customer_recordSignaturChecksum", length = 20)
	private String recordSignaturChecksum;
	
	@Column(name = "customer_gender", nullable = false, length = 20)
	private String gender;
	
//	@DateTimeFormat(pattern="MM/dd/yyyy")
//    @NotNull @Past
	@Column(name = "customer_birthday", nullable = false, length = 20)
	private String birthday;
	
	@Column(name = "customer_currency", nullable = false, length = 20)
	private String currency;
	
	//@OneToMany(mappedBy = "customer")
	//private List<PrivateKey> privateKeys;
	
	//@OneToMany(mappedBy = "customer")
	//private List<PublicKey> publicKeys;

	/*@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOAuth() {
		return OAuth;
	}

	public void setOAuth(String oAuth) {
		OAuth = oAuth;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBankAccountIban() {
		return bankAccountIban;
	}

	public void setBankAccountIban(String bankAccountIban) {
		this.bankAccountIban = bankAccountIban;
	}

	public String getBankAccountBICC() {
		return bankAccountBICC;
	}

	public void setBankAccountBICC(String bankAccountBICC) {
		this.bankAccountBICC = bankAccountBICC;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPinCodeHash() {
		return pinCodeHash;
	}

	public void setPinCodeHash(String pinCodeHash) {
		this.pinCodeHash = pinCodeHash;
	}

	public String getBankValidationHash() {
		return bankValidationHash;
	}

	public void setBankValidationHash(String bankValidationHash) {
		this.bankValidationHash = bankValidationHash;
	}

	public String getPwHash() {
		return pwHash;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}

	public String getPwHintQuestion1() {
		return pwHintQuestion1;
	}

	public void setPwHintQuestion1(String pwHintQuestion1) {
		this.pwHintQuestion1 = pwHintQuestion1;
	}

	public String getPwHintAnswer1() {
		return pwHintAnswer1;
	}

	public void setPwHintAnswer1(String pwHintAnswer1) {
		this.pwHintAnswer1 = pwHintAnswer1;
	}

	public String getPwHintQuestion2() {
		return pwHintQuestion2;
	}

	public void setPwHintQuestion2(String pwHintQuestion2) {
		this.pwHintQuestion2 = pwHintQuestion2;
	}

	public String getPwHintAnswer2() {
		return pwHintAnswer2;
	}

	public void setPwHintAnswer2(String pwHintAnswer2) {
		this.pwHintAnswer2 = pwHintAnswer2;
	}

	public String getRecordSignaturChecksum() {
		return recordSignaturChecksum;
	}

	public void setRecordSignaturChecksum(String recordSignaturChecksum) {
		this.recordSignaturChecksum = recordSignaturChecksum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", nickname=" + nickname + ", OAuth=" + OAuth + ", forename=" + forename
				+ ", lastname=" + lastname + ", bankAccountIban=" + bankAccountIban + ", bankAccountBICC="
				+ bankAccountBICC + ", eMail=" + eMail + ", pinCodeHash=" + pinCodeHash + ", bankValidationHash="
				+ bankValidationHash + ", pwHash=" + pwHash + ", pwHintQuestion1=" + pwHintQuestion1
				+ ", pwHintAnswer1=" + pwHintAnswer1 + ", pwHintQuestion2=" + pwHintQuestion2 + ", pwHintAnswer2="
				+ pwHintAnswer2 + ", recordSignaturChecksum=" + recordSignaturChecksum + ", gender=" + gender
				+ ", birthday=" + birthday + ", currency=" + currency
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((OAuth == null) ? 0 : OAuth.hashCode());
		result = prime * result + ((bankAccountBICC == null) ? 0 : bankAccountBICC.hashCode());
		result = prime * result + ((bankAccountIban == null) ? 0 : bankAccountIban.hashCode());
		result = prime * result + ((bankValidationHash == null) ? 0 : bankValidationHash.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + ((forename == null) ? 0 : forename.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((pinCodeHash == null) ? 0 : pinCodeHash.hashCode());
		result = prime * result + ((pwHash == null) ? 0 : pwHash.hashCode());
		result = prime * result + ((pwHintAnswer1 == null) ? 0 : pwHintAnswer1.hashCode());
		result = prime * result + ((pwHintAnswer2 == null) ? 0 : pwHintAnswer2.hashCode());
		result = prime * result + ((pwHintQuestion1 == null) ? 0 : pwHintQuestion1.hashCode());
		result = prime * result + ((pwHintQuestion2 == null) ? 0 : pwHintQuestion2.hashCode());
		result = prime * result + ((recordSignaturChecksum == null) ? 0 : recordSignaturChecksum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (OAuth == null) {
			if (other.OAuth != null)
				return false;
		} else if (!OAuth.equals(other.OAuth))
			return false;
		if (bankAccountBICC == null) {
			if (other.bankAccountBICC != null)
				return false;
		} else if (!bankAccountBICC.equals(other.bankAccountBICC))
			return false;
		if (bankAccountIban == null) {
			if (other.bankAccountIban != null)
				return false;
		} else if (!bankAccountIban.equals(other.bankAccountIban))
			return false;
		if (bankValidationHash == null) {
			if (other.bankValidationHash != null)
				return false;
		} else if (!bankValidationHash.equals(other.bankValidationHash))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (forename == null) {
			if (other.forename != null)
				return false;
		} else if (!forename.equals(other.forename))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (pinCodeHash == null) {
			if (other.pinCodeHash != null)
				return false;
		} else if (!pinCodeHash.equals(other.pinCodeHash))
			return false;
		if (pwHash == null) {
			if (other.pwHash != null)
				return false;
		} else if (!pwHash.equals(other.pwHash))
			return false;
		if (pwHintAnswer1 == null) {
			if (other.pwHintAnswer1 != null)
				return false;
		} else if (!pwHintAnswer1.equals(other.pwHintAnswer1))
			return false;
		if (pwHintAnswer2 == null) {
			if (other.pwHintAnswer2 != null)
				return false;
		} else if (!pwHintAnswer2.equals(other.pwHintAnswer2))
			return false;
		if (pwHintQuestion1 == null) {
			if (other.pwHintQuestion1 != null)
				return false;
		} else if (!pwHintQuestion1.equals(other.pwHintQuestion1))
			return false;
		if (pwHintQuestion2 == null) {
			if (other.pwHintQuestion2 != null)
				return false;
		} else if (!pwHintQuestion2.equals(other.pwHintQuestion2))
			return false;
		if (recordSignaturChecksum == null) {
			if (other.recordSignaturChecksum != null)
				return false;
		} else if (!recordSignaturChecksum.equals(other.recordSignaturChecksum))
			return false;
		return true;
	}
}
