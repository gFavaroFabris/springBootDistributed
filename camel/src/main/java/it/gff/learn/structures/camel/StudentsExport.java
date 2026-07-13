package it.gff.learn.structures.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StudentsExport extends RouteBuilder {
    @Autowired
    DataSource dataSource;

    @Value("${camel.ftp.uri}")
    private String ftpURI;
    private StudentConverter mapper = new StudentConverter();

    @Override
    public void configure() throws Exception {

        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(ExportStudent.class);
        from("timer:simple?period=60000")
                .setBody(constant("select * from students where exported=FALSE"))
                .to("jdbc:dataSource")
                .choice()
                .when(simple("${body.isEmpty()}"))
                .log("No students to export...")
                .otherwise()
                .split(body())
                .bean(mapper, "toStudent")
                .setHeader("StudentID", simple("${body.id}"))
                .setHeader("CamelFileName", simple("${body.id}.json"))
                .process(this::log)
                .marshal(jsonDataFormat)
                .to(ftpURI)
                .setBody(simple("UPDATE students SET exported = TRUE where id='${header.StudentID}'"))
                .to("jdbc:dataSource")
                .end();
    }

    private void log(Exchange exchange) {
        System.out.println("Exporting student " + ((ExportStudent)exchange.getIn().getBody()).getId());
    }
}