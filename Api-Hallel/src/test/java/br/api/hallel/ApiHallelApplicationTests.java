package br.api.hallel;

import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.repository.SorteioRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Log4j2
class ApiHallelApplicationTests {

	@Autowired
	private SorteioRepository repository;

	@Test
	void contextLoads() throws ParseException {


	}

}
