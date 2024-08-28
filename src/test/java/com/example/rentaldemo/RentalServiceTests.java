package com.example.rentaldemo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rentaldemo.enums.ToolType;
import com.example.rentaldemo.model.RentalAgreement;
import com.example.rentaldemo.model.Tool;
import com.example.rentaldemo.model.ToolCharge;
import com.example.rentaldemo.repository.ToolChargeRepository;
import com.example.rentaldemo.repository.ToolRepository;
import com.example.rentaldemo.service.IHolidayService;
import com.example.rentaldemo.service.RentalService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@SpringBootTest
class RentalServiceTests {

	@Mock
	private ToolRepository toolRepositoryMock;

	@Mock
	private ToolChargeRepository toolChargeRepositoryMock;

	@Mock
	private IHolidayService holidayServiceMock;

	@InjectMocks
	private RentalService uut;

	@Test
	void testGenerateRentalAgreement_Test2() {
		Tool tool = Tool.builder()
			.code("LADW")
			.type(ToolType.LADDER)
			.brand("Werner")
			.build();
		ToolCharge toolCharge = ToolCharge.builder()
			.type(ToolType.LADDER)
			.daily(1.99)
			.weekday(true)
			.weekend(true)
			.holiday(false)
			.build();
		Mockito.when(toolRepositoryMock.findByCode(Mockito.any(String.class))).thenReturn(tool);
		Mockito.when(toolChargeRepositoryMock.findByType(Mockito.any(ToolType.class))).thenReturn(toolCharge);
		Mockito.when(holidayServiceMock.isHoliday(LocalDate.of(2020, 7, 3))).thenReturn(true);

		RentalAgreement res = uut.generateRentalAgreement("LADW", 3, 10, LocalDate.of(2020, 7, 2));
		
		assertEquals(tool, res.getTool());
		assertEquals(3, res.getRentalDays());
		assertEquals(LocalDate.of(2020,7,2), res.getCheckoutDate());
		assertEquals(LocalDate.of(2020,7,5), res.getDueDate());
		assertEquals(1.99, res.getDailyRentalCharge());
		assertEquals(2, res.getChargeDays());
		assertEquals(3.98, res.getPreDiscountCharge());
		assertEquals(10, res.getDiscountPercent());
		assertEquals(0.4, res.getDiscountAmount());
		assertEquals(3.58, res.getFinalCharge());
	}

	@Test
	void testGenerateRentalAgreement_Test3() {
		Tool tool = Tool.builder()
			.code("CHNS")
			.type(ToolType.CHAINSAW)
			.brand("Stihnl")
			.build();
		ToolCharge toolCharge = ToolCharge.builder()
			.type(ToolType.CHAINSAW)
			.daily(1.49)
			.weekday(true)
			.weekend(false)
			.holiday(true)
			.build();
		Mockito.when(toolRepositoryMock.findByCode(Mockito.any(String.class))).thenReturn(tool);
		Mockito.when(toolChargeRepositoryMock.findByType(Mockito.any(ToolType.class))).thenReturn(toolCharge);
		Mockito.when(holidayServiceMock.isHoliday(LocalDate.of(2015, 7, 3))).thenReturn(true);

		RentalAgreement res = uut.generateRentalAgreement("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
		
		assertEquals(tool, res.getTool());
		assertEquals(5, res.getRentalDays());
		assertEquals(LocalDate.of(2015,7,2), res.getCheckoutDate());
		assertEquals(LocalDate.of(2015,7,7), res.getDueDate());
		assertEquals(1.49, res.getDailyRentalCharge());
		assertEquals(3, res.getChargeDays());
		assertEquals(4.47, res.getPreDiscountCharge());
		assertEquals(25, res.getDiscountPercent());
		assertEquals(1.12, res.getDiscountAmount());
		assertEquals(3.35, res.getFinalCharge());
	}

	@Test
	void testGenerateRentalAgreement_Test4() {
		Tool tool = Tool.builder()
			.code("JAKD")
			.type(ToolType.JACKHAMMER)
			.brand("DeWalt")
			.build();
		ToolCharge toolCharge = ToolCharge.builder()
			.type(ToolType.JACKHAMMER)
			.daily(2.99)
			.weekday(true)
			.weekend(false)
			.holiday(false)
			.build();
		Mockito.when(toolRepositoryMock.findByCode(Mockito.any(String.class))).thenReturn(tool);
		Mockito.when(toolChargeRepositoryMock.findByType(Mockito.any(ToolType.class))).thenReturn(toolCharge);
		Mockito.when(holidayServiceMock.isHoliday(LocalDate.of(2015, 9, 7))).thenReturn(true);

		RentalAgreement res = uut.generateRentalAgreement("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
		
		assertEquals(tool, res.getTool());
		assertEquals(6, res.getRentalDays());
		assertEquals(LocalDate.of(2015,9,3), res.getCheckoutDate());
		assertEquals(LocalDate.of(2015,9,9), res.getDueDate());
		assertEquals(2.99, res.getDailyRentalCharge());
		assertEquals(3, res.getChargeDays());
		assertEquals(8.97, res.getPreDiscountCharge());
		assertEquals(0, res.getDiscountPercent());
		assertEquals(0, res.getDiscountAmount());
		assertEquals(8.97, res.getFinalCharge());
	}

	@Test
	void testGenerateRentalAgreement_Test5() {
		Tool tool = Tool.builder()
			.code("JAKR")
			.type(ToolType.JACKHAMMER)
			.brand("Ridgid")
			.build();
		ToolCharge toolCharge = ToolCharge.builder()
			.type(ToolType.JACKHAMMER)
			.daily(2.99)
			.weekday(true)
			.weekend(false)
			.holiday(false)
			.build();
		Mockito.when(toolRepositoryMock.findByCode(Mockito.any(String.class))).thenReturn(tool);
		Mockito.when(toolChargeRepositoryMock.findByType(Mockito.any(ToolType.class))).thenReturn(toolCharge);
		Mockito.when(holidayServiceMock.isHoliday(LocalDate.of(2015, 7, 3))).thenReturn(true);

		RentalAgreement res = uut.generateRentalAgreement("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
		
		assertEquals(tool, res.getTool());
		assertEquals(9, res.getRentalDays());
		assertEquals(LocalDate.of(2015,7,2), res.getCheckoutDate());
		assertEquals(LocalDate.of(2015,7,11), res.getDueDate());
		assertEquals(2.99, res.getDailyRentalCharge());
		assertEquals(6, res.getChargeDays());
		assertEquals(17.94, res.getPreDiscountCharge());
		assertEquals(0, res.getDiscountPercent());
		assertEquals(0, res.getDiscountAmount());
		assertEquals(17.94, res.getFinalCharge());
	}

	@Test
	void testGenerateRentalAgreement_Test6() {
		Tool tool = Tool.builder()
			.code("JAKR")
			.type(ToolType.JACKHAMMER)
			.brand("Ridgid")
			.build();
		ToolCharge toolCharge = ToolCharge.builder()
			.type(ToolType.JACKHAMMER)
			.daily(2.99)
			.weekday(true)
			.weekend(false)
			.holiday(false)
			.build();
		Mockito.when(toolRepositoryMock.findByCode(Mockito.any(String.class))).thenReturn(tool);
		Mockito.when(toolChargeRepositoryMock.findByType(Mockito.any(ToolType.class))).thenReturn(toolCharge);
		Mockito.when(holidayServiceMock.isHoliday(LocalDate.of(2020, 7, 3))).thenReturn(true);

		RentalAgreement res = uut.generateRentalAgreement("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
		
		assertEquals(tool, res.getTool());
		assertEquals(4, res.getRentalDays());
		assertEquals(LocalDate.of(2020,7,2), res.getCheckoutDate());
		assertEquals(LocalDate.of(2020,7,6), res.getDueDate());
		assertEquals(2.99, res.getDailyRentalCharge());
		assertEquals(1, res.getChargeDays());
		assertEquals(2.99, res.getPreDiscountCharge());
		assertEquals(50, res.getDiscountPercent());
		assertEquals(1.5, res.getDiscountAmount());
		assertEquals(1.49, res.getFinalCharge());
	}
}
