package application;

public class employee {
	int id,passward,age,phone;
	String name , gender;
	public employee(int id, int passward, String name, String gender, int phone,int age) {
		super();
		this.id = id;
		this.passward = passward;
		this.age = age;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPassward() {
		return passward;
	}
	public void setPassward(int passward) {
		this.passward = passward;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String toString()
	{
	    return "EID		EPASSWORD		ENAME		EGENDER		EPHONENO		EAGE"
	    		+this.getId() +"	"+this.getPassward()+"	"+this.getName()+"	"+this.getGender()+
	    		"	"+this.getPhone()+"	  "+this.getAge();
	    
	}
}

