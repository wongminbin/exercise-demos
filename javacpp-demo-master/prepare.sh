cd src/main/java
cp ~/.m2/repository/org/bytedeco/javacpp/1.1/javacpp-1.1.jar javacpp.jar
javac -cp javacpp.jar sample/VirtualSample.java
java -jar javacpp.jar sample/VirtualSample
rm javacpp.jar
rm sample/*.class
