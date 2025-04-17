package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }
        
        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);
        
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible);
        
        int tax = (int) Math.round(0.05 * (annualIncome - nonTaxableIncome));
        
        return Math.max(tax, 0);
    }
	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = BASE_NON_TAXABLE_INCOME;
        if (isMarried) {
            nonTaxableIncome += MARRIAGE_ALLOWANCE;
        }
        nonTaxableIncome += numberOfChildren * CHILD_ALLOWANCE;
        return nonTaxableIncome;
    }

    private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;
    }
	
}
