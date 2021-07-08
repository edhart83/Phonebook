package phonebook;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import person.Person;
import address.Address;

public class Phonebook {
	public static boolean powerOn = false;
	public static Person[] phoneList = new Person[0];

	public static void main(String[] args) {
		do {
			System.out.println("(Number of Records in the System: " + phoneList.length + ")\n"
					+ "Please choose an option below:\n" 
					+ "1) Add new record\n" 
					+ "2) Delete a record\n"
					+ "3) Search by First Name\n" 
					+ "4) Search by Last Name\n" 
					+ "5) Search by Full Name\n"
					+ "6) Search by Phone Number\n" 
					+ "7) Search by City or State\n" 
					+ "8) Enter phone number to delete record\n" 
					+ "9) Enter phone number to update record\n"
					+ "10) Show all records\n" 
					+ "11) Exit");
			Scanner input = new Scanner(System.in);
			int option = input.nextInt();
			switch (option) {
			case 1:
				System.out.println(
						"Please paste in contact information in this format: John Doe, 114 Market St, St Louis, MO, 63403, 6366435698"); 
				Scanner input1 = new Scanner(System.in);
				String inputString = input1.nextLine();
				phoneList = processData(phoneList, inputString);
				break;
			case 2:
				System.out.println("Enter phone number to delete record: ");
				Scanner input2 = new Scanner(System.in);
				String recordToDelete = input2.nextLine();
				phoneList = deleteByPhoneNumber(phoneList, recordToDelete);
				break;
			case 3:
				System.out.println("Enter first name: ");
				Scanner input3 = new Scanner(System.in);
				String firstNameToSearch = input3.next();
				searchByFirstName(phoneList, firstNameToSearch);
				break;
			case 4:
				System.out.println("Enter last name: ");
				Scanner input4 = new Scanner(System.in);
				String lastNameToSearch = input4.next();
				searchByLastName(phoneList, lastNameToSearch);
			case 5:
				System.out.println("Enter full name: ");
				Scanner input5 = new Scanner(System.in);
				String fullNameToSearch = input5.nextLine();
				searchByFullName(phoneList, fullNameToSearch);
				break;
			case 6:
				System.out.println("Enter phone number: ");
				Scanner input6 = new Scanner(System.in);
				String phoneNumberToSearch = input6.nextLine();
				searchByPhoneNumber(phoneList, phoneNumberToSearch);
				break;
			case 7:
				System.out.println("Enter city or state: ");
				Scanner input7 = new Scanner(System.in);
				String cityOrStateToSearch = input7.nextLine();
				searchByCityOrState(phoneList, cityOrStateToSearch);
				break;
			case 8:
				System.out.println("Enter phone number to delete record: ");
				Scanner input8 = new Scanner(System.in);
				String phoneNumberToDelete = input8.nextLine();
				phoneList = deleteByPhoneNumber(phoneList, phoneNumberToDelete);
				break;
			case 9:
				System.out.println("Enter phone number to update record: ");
				Scanner input9 = new Scanner(System.in);
				String phoneNumberToUpdate = input9.nextLine();
				phoneList = updateByPhoneNumber(phoneList, phoneNumberToUpdate);
				break;
			case 10:
				System.out.println("Show all records: ");
				showAllEntries(phoneList);
				break;
			case 11:
				System.out.println("Phonebook closed.");
				System.exit(0);
				break;
			default:
				System.out.println("Please make a valid selection");
			}
		} while (!powerOn);
	}

	public static Person[] processData(Person[] phoneList, String inputString) {
		Person tempPerson = new Person();
		String[] newRecordArray = inputString.split(", ");
		String[] nameData = grabNameArr(newRecordArray[0]);
		tempPerson.setFirstName(nameData[0]);
		tempPerson.setMiddleName1(nameData[1]);
		tempPerson.setMiddleName2(nameData[2]);
		tempPerson.setLastName(nameData[3]);
		Address tempAddress = new Address(newRecordArray[1], newRecordArray[2], newRecordArray[3], newRecordArray[4]);
		tempPerson.setAddress(tempAddress);
		tempPerson.setPhoneNumber(newRecordArray[5]);
		return addNewRecord(phoneList, tempPerson);
	}

	public static String[] grabNameArr(String fullName) {
		String[] nameSplit = new String[4]; // This array will hold our final product
		String[] nameArray = new String[4]; // This array will hold the full name after we split it
		// Split the full name by spaces and put it into nameArray from above
		nameArray = fullName.split(" ");
		int length = nameArray.length;
		int lastIndexPosition = nameArray.length - 1;
		String firstName = "";
		String lastName = "";
		String middleName1 = "";
		String middleName2 = "";
		// This switch determines how many names were brought in from the fullName
		// string and sets accordingly
		switch (length) {
		case 2:
			firstName = nameArray[0];
			lastName = nameArray[lastIndexPosition];
			break;
		case 3:
			firstName = nameArray[0];
			middleName1 = nameArray[1];
			lastName = nameArray[lastIndexPosition];
			break;
		case 4:
			firstName = nameArray[0];
			middleName1 = nameArray[1];
			middleName2 = nameArray[2];
			lastName = nameArray[lastIndexPosition];
			break;
		default:
			System.out.println("Please only enter your first and last name, and maximum of 2 middle names");
		}
		// Now lets load up our name values and return it
		nameSplit[0] = firstName;
		nameSplit[1] = middleName1;
		nameSplit[2] = middleName2;
		nameSplit[3] = lastName;
		return nameSplit;
	}

	public static Person[] addNewRecord(Person[] phoneList, Person tempPerson) {
		Person[] tempArray = new Person[phoneList.length + 1];
		for (int i = 0; i < phoneList.length; i++) {
			tempArray[i] = phoneList[i];
		}
		tempArray[tempArray.length - 1] = tempPerson;
		return tempArray;
	}

	public static Person[] deleteByPhoneNumber(Person[] phoneList, String phoneNumber) {
		Person[] tempArray = new Person[phoneList.length - 1];
		int count = 0;
		for (int i = 0; i < phoneList.length; i++) {
			String convertPhoneNumber = phoneList[i].getPhoneNumber().replaceAll(" ", "");
			if (!convertPhoneNumber.equals(phoneNumber)) {
				tempArray[count] = phoneList[i];
				count++;
			}
		}
		System.out.println("Record deleted.");
		return tempArray;
	}

	public static void searchByFirstName(Person[] phoneList, String firstName) {
		for (int i = 0; i < phoneList.length; i++) {
			String convertFirstName = phoneList[i].getFirstName();
			if (convertFirstName.equals(firstName)) {
				System.out.println("---------------");
				System.out.println(phoneList[i].toString());
			}
		}
	}
	
	public static void searchByLastName(Person[] phoneList, String lastName) {
		for (int i = 0; i < phoneList.length; i++) {
			String convertLastName = phoneList[i].getLastName();
			if (convertLastName.equals(lastName)) {
				System.out.println("---------------");
				System.out.println(phoneList[i].toString());
			}
		}
	}
	
	public static void searchByFullName(Person[] phoneList, String fullName) {
		String[] nameData = grabNameArr(fullName);
		for (int i = 0; i < phoneList.length; i++) {
			String convertFirstName = phoneList[i].getFirstName();
			String convertMiddleName1 = phoneList[i].getMiddleName1();
			String convertMiddleName2 = phoneList[i].getMiddleName2();
			String convertLastName = phoneList[i].getLastName();
			//This makes sure we don't grab records we don't want if they are similar
			if (convertFirstName.equals(nameData[0]) && nameData[1].equals(null) && nameData[2].equals(null) && convertLastName.equals(nameData[3]) ||
				convertFirstName.equals(nameData[0]) && convertMiddleName1.equals(nameData[1]) && nameData[2].equals(null) && convertMiddleName2.equals(nameData[3]) ||
				convertFirstName.equals(nameData[0]) && convertMiddleName1.equals(nameData[1]) && convertMiddleName2.equals(nameData[2]) && convertLastName.equals(nameData[3])){
				System.out.println("---------------");
				System.out.println(" First: " + phoneList[i].getFirstName()
					+ " Middle 1: " + phoneList[i].getMiddleName1()
					+ " Middle 2: " + phoneList[i].getMiddleName2()
					+ " Last: " + phoneList[i].getLastName()
					+ " Address: " + phoneList[i].getAddress().toString()
					+ " Phone: " + phoneList[i].getPhoneNumber());
			}
		}
	}
	
	public static void searchByPhoneNumber(Person[] phoneList, String phoneNumber) {
		for (int i = 0; i < phoneList.length; i++) {
			String convertPhoneNumber = phoneList[i].getPhoneNumber();
			if (convertPhoneNumber.equals(phoneNumber)){
				System.out.println("---------------");
				System.out.println(" First: " + phoneList[i].getFirstName()
					+ " Middle 1: " + phoneList[i].getMiddleName1()
					+ " Middle 2: " + phoneList[i].getMiddleName2()
					+ " Last: " + phoneList[i].getLastName()
					+ " Address: " + phoneList[i].getAddress().toString()
					+ " Phone: " + phoneList[i].getPhoneNumber());
			}
		}
	}
	
	public static void searchByCityOrState(Person[] phoneList, String cityOrState) {
		for (int i = 0; i < phoneList.length; i++) {
			//NOTE***Had an issue with extra space coming into array. I used replaceAll to get rid of them.
			//https://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java/36444332
			String convertCity = phoneList[i].getAddress().getCity().replaceAll(" ", "");
			String convertState = phoneList[i].getAddress().getStateAbbreviation().replaceAll(" ", "");
			System.out.println("We are here");
			if (convertCity.equals(cityOrState) || convertState.equals(cityOrState)){
				System.out.println("---------------");
				System.out.println(" First: " + phoneList[i].getFirstName()
					+ " Middle 1: " + phoneList[i].getMiddleName1()
					+ " Middle 2: " + phoneList[i].getMiddleName2()
					+ " Last: " + phoneList[i].getLastName()
					+ " Address: " + phoneList[i].getAddress().toString()
					+ " Phone: " + phoneList[i].getPhoneNumber());
			}
		}
	}
	
	public static Person[] updateByPhoneNumber(Person[] phoneList, String phoneNumber) {
		//Ask user what field they want to update
		System.out.println("What would you like to update? "
				+ "\n 1) First Name: "
				+ "\n 2) Last Name: "
				+ "\n 3) Address: "
				+ "\n 4) Phone Number: ");
		Scanner updateInput = new Scanner(System.in);
		int updateSelection = updateInput.nextInt();
		switch (updateSelection) {
		case 1: 
			System.out.println("Enter new first name: ");
			Scanner inputNewFirstName = new Scanner(System.in);
			String newFirstName = inputNewFirstName.nextLine();
			for(int i =0; i < phoneList.length;i++)
			{
				//.trim removes spaces. is easier to use than replaceAll
				if(phoneList[i].getPhoneNumber().trim().equals(phoneNumber))
				{
					phoneList[i].setFirstName(newFirstName);
					break;
				}
			}
			break;
		case 2: 
			System.out.println("Enter new last name: ");
			Scanner inputNewLastName = new Scanner(System.in);
			String newLastName = inputNewLastName.nextLine();
			for(int i =0; i < phoneList.length;i++)
			{
				if(phoneList[i].getPhoneNumber().trim().equals(phoneNumber))
				{
					phoneList[i].setLastName(newLastName);
				}
			}
			break;
		case 3: 
			System.out.println("Enter new street: ");
			Scanner inputNewStreet = new Scanner(System.in);
			String newStreet = inputNewStreet.nextLine();
			System.out.println("Enter new city: ");
			Scanner inputNewCity = new Scanner(System.in);
			String newCity = inputNewCity.nextLine();
			System.out.println("Enter new state: ");
			Scanner inputNewState = new Scanner(System.in);
			String newState = inputNewState.nextLine();
			System.out.println("Enter new zip: ");
			Scanner inputNewZip = new Scanner(System.in);
			String newZip = inputNewZip.nextLine();
			Address newAddress = new Address(newStreet, newCity, newState, newZip);
			for (int i = 0; i < phoneList.length; i++)
			{
				if (phoneList[i].getPhoneNumber().trim().equals(phoneNumber)) 
				{
					phoneList[i].setAddress(newAddress);
				}
			}
			break;
		case 4: 
			System.out.println("Enter new phone number: ");
			Scanner inputNewPhoneNumber = new Scanner(System.in);
			String newPhoneNumber = inputNewPhoneNumber.nextLine();
			for (int i = 0; i < phoneList.length; i++)
			{
				if (phoneList[i].getPhoneNumber().trim().equals(phoneNumber)) 
				{
					phoneList[i].setPhoneNumber(newPhoneNumber);
				}
			}
			break;
		default:
			System.out.println("Please enter a valid selection");
			break;
		}
		
		
		System.out.println("Record updated.");
		return phoneList;
		}
	
	public static void showAllEntries(Person[] phoneList) {
		Arrays.sort(phoneList, Collections.reverseOrder(new Comparator<Person>() {
		     @Override
		     public int compare(Person a1, Person a2) {
		     // Used -1 to reverseOrder for ASC descending using String.compareTo function
		      return -1* a1.getFirstName().compareTo(a2.getFirstName());
		      }
		  }));
	
		for (int i = 0; i < phoneList.length; i++) {
			System.out.println("First: " + phoneList[i].getFirstName()
					+ " Middle 1: " + phoneList[i].getMiddleName1()
					+ " Middle 2: " + phoneList[i].getMiddleName2()
					+ " Last: " + phoneList[i].getLastName()
					+ " Address: " + phoneList[i].getAddress().toString()
					+ " Phone: " + phoneList[i].getPhoneNumber());
		}
	}
}
