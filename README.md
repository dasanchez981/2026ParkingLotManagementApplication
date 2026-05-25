# 2026ParkingLotManagementApplication

# compile
javac src/Parking.java

# run test case
java -cp src Parking tests/input1.txt > tests/actual1.txt

# verify test case
diff tests/actual1.txt tests/expected1.txt
