cd src/main/java
cp ~/.m2/repository/org/bytedeco/javacpp/1.1/javacpp-1.1.jar javacpp.jar
javac -cp javacpp.jar otp/Sample.java
java -jar javacpp.jar otp/Sample
rm javacpp.jar
rm otp/*.class
