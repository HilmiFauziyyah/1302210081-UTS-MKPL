package lib;

public class TaxFunction {

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
	 * dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan
	 * (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi
	 * pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
	 * pajaknya adalah Rp 54.000.000.
	 * 
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
	 * sebesar Rp 4.500.000.
	 * 
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
	 * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 * @param annualDeductible
	 * @param monthWorkingInYear
	 * 
	 */

	public static int calculateTax(int Salary, int deductible,
			int monthWorkingInYear, int annualDeductible, boolean isMarried, int numberOfChildren) {

		int tax = (int) Math.round(0.05 * (Salary - deductible - 54000000));

		if (Salary > 4500000 * 12) {
			System.err.println("More than 12 month working per year");
		}

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}

		if (isMarried) {
			tax = tax + 4500000 + (numberOfChildren * 1500000);
		}

		return tax;

	}

}
