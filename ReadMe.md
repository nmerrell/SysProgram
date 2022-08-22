# SysProgram

This application is designed to check a mount point (i.e. /tmp) on a 
system (remote or local) in order to retrieve the list of files and their respective size (in bytes).

## Execute Program

There are two (2) ways to run this application.

```bash
java -jar SysProgram-v1.0.java
```

or

```bash
java -jar SysProgram-v1.0.java <LOCATION>
```
## Example

```bash
java -jar SysProgram-v1.0.java /tmp/

## This will return something along the lines of
{
  "files:" [
    {"/tmp/Test.txt", 123},
    {"/tmp/Test1.txt", 456},
    {"/tmp/folder/Test3.txt", 789}
  ]
}

```
If you don't pass in a location to check, then the application will prompt the user.
```bash
java -jar SysProgram-v1.0.java

Enter the desired mount location to check: 
<LOCATION>
Check this location (Y/N)? 
  "<LOCATION>"
```
The output will be the same as the first example.

## Additional Information

There is also an option to export the results to a file named "output.txt" to a specified location
from the user. 

After the user prints out the value to the screen and/or the TXT file, the system will 
prompt the user if they wish to check another location. 