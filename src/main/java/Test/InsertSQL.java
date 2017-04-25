package Test;

/**
 * Created by ArtStyle on 25.04.2017.
 */
public class InsertSQL {

    public static void main(String[] args) {
//        for(int i=2007; i<=2050;i++){
//            System.out.println("INSERT INTO Portfolio (id, quarter, totalTouristCount, armTouristCount, otherTouristCount, finances, icTouristCount, icMaleCount, icFemaleCount, icVisitDecription, socialPackageCount, column_12, isTourOperator, age15, age30, age50, age51, year, program1, program2, program3, program4, program5, program6, financeArm, financeForeign, totalFinanceArm, totalFinanceForeign, icStepanakert, icShushi, icTigranakert, icTsaxkashat) VALUES (null, 1, 0, 0, 0, 0, 0, 0, 0, '', 0, 0, 0, 0, 0, 0, 0, "+ i +", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);\n" +
//                    "INSERT INTO Portfolio (id, quarter, totalTouristCount, armTouristCount, otherTouristCount, finances, icTouristCount, icMaleCount, icFemaleCount, icVisitDecription, socialPackageCount, column_12, isTourOperator, age15, age30, age50, age51, year, program1, program2, program3, program4, program5, program6, financeArm, financeForeign, totalFinanceArm, totalFinanceForeign, icStepanakert, icShushi, icTigranakert, icTsaxkashat) VALUES (null, 2, 0, 0, 0, 0, 0, 0, 0, '', 0, 0, 0, 0, 0, 0, 0, "+ i +", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);\n" +
//                    "INSERT INTO Portfolio (id, quarter, totalTouristCount, armTouristCount, otherTouristCount, finances, icTouristCount, icMaleCount, icFemaleCount, icVisitDecription, socialPackageCount, column_12, isTourOperator, age15, age30, age50, age51, year, program1, program2, program3, program4, program5, program6, financeArm, financeForeign, totalFinanceArm, totalFinanceForeign, icStepanakert, icShushi, icTigranakert, icTsaxkashat) VALUES (null, 3, 0, 0, 0, 0, 0, 0, 0, '', 0, 0, 0, 0, 0, 0, 0, "+ i +", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);\n" +
//                    "INSERT INTO Portfolio (id, quarter, totalTouristCount, armTouristCount, otherTouristCount, finances, icTouristCount, icMaleCount, icFemaleCount, icVisitDecription, socialPackageCount, column_12, isTourOperator, age15, age30, age50, age51, year, program1, program2, program3, program4, program5, program6, financeArm, financeForeign, totalFinanceArm, totalFinanceForeign, icStepanakert, icShushi, icTigranakert, icTsaxkashat) VALUES (null, 4, 0, 0, 0, 0, 0, 0, 0, '', 0, 0, 0, 0, 0, 0, 0, "+ i +", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
//        }

//        for(int i=2007; i<=2050;i++){
//            System.out.println("INSERT INTO Year (id, year) VALUES ("+ i +", "+ i +");");
//        }

        for(int i=2007; i<=2050;i++){
            System.out.println("INSERT INTO YearlyInforamtion (id, GDP, overnightDuration, ancaket1, ancaket2, ancaket3, amcaket4, ancaket5, yearId, program1, program2, program3, program4, program5, program6) VALUES (null, null, null, null, null, null, null, null, "+ i +", 0, 0, 0, 0, 0, 0);");
        }
    }
}
