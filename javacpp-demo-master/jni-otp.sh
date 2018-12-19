cd src/main/java
cp ~/.m2/repository/org/bytedeco/javacpp/1.4.2/javacpp-1.4.2.jar javacpp.jar
javac -cp javacpp.jar com/phs/projects/otp/jni/OTP.java
java -jar javacpp.jar com/phs/projects/otp/jni/OTP
rm javacpp.jar
rm com/phs/projects/otp/jni/*.class
