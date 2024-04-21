package lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String EmployeeName;
	private String idNumber;
	private String address;

	private LocalDate DateJoined;
	private int monthWorkingInYear;

	private boolean Foreigner;
	private boolean gender; // true = Laki-laki, false = Perempuan
	private boolean IsMarried;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String employeeId, String EmployeeName, String idNumber, String address,
			String DateJoined, boolean Foreigner, boolean gender, boolean IsMarried) {
		this.employeeId = employeeId;
		this.EmployeeName = EmployeeName;
		this.idNumber = idNumber;
		this.address = address;
		this.Foreigner = Foreigner;
		this.gender = gender;
		this.IsMarried = IsMarried;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.DateJoined = LocalDate.parse(DateJoined, formatter);
		;

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * grade 1: 3.000.000 per bulan
	 * grade 2: 5.000.000 per bulan
	 * grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		if (grade == 1) {
			monthlySalary = 3000000;
			if (Foreigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 2) {
			monthlySalary = 5000000;
			if (Foreigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 3) {
			monthlySalary = 7000000;
			if (Foreigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {

		/**
		 * Menghitung berapa lama pegawai bekerja dalam setahun ini,
		 * jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap
		 * 12bulan.
		 */

		LocalDate date = LocalDate.now();

		if (date.getYear() == DateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - DateJoined.getMonthValue();
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				IsMarried, childIdNumbers.size());
	}
}
