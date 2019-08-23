
# Spring Boot with Spring Batch Example 1
## Load CSV to CSV
- `http://localhost:8081/load` - Trigger point for Spring Batch
- `http://localhost:8081/h2-console` - H2 Console for querying the in-memory tables.

http://localhost:8081/load/task  use to lo inout file and write in out file by adding some extra data.


https://dzone.com/articles/spring-batch-typical-use-case

https://github.com/drapostolos/rdp4j/wiki/User-Guide
https://www.baeldung.com/java-file-sftp
https://dzone.com/articles/spring-integration-sftp-upload-example-using-key-b


https://www.javagists.com/how-to-download-and-upload-a-file-through-sftp-using-java

https://www.example-code.com/java/sftp_file_exists.asp

https://www.programcreek.com/java-api-examples/?code=DevComPack/setupmaker/setupmaker-master/src/main/java/com/dcp/sm/web/sftp/JschFactory.java#

STEP: 
   1: Read data from SFTP:
   2: Read Data from Database (Use Named Queries)
   3: Merge Step 1 and step 2 data 
   4: Send Modified data into Remote SMTP server.
   5: Delete locally download file from tmp folder
  
  Read SMTP configuration from application properties:
  Read data bases from application properties
  Need Proper Data base query results
  Delete from local file.
  Need proper logs
  Re-try if any thing fail in middle
  Resume process.
  
   
   
   https://examples.javacodegeeks.com/enterprise-java/spring/spring-batch-tasklet-example/
   