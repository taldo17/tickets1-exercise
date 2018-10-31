package tickets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by taldo on 22/08/2017.
 */
public class TicketService {

    private WindowsFileWriter windowsFileWriter = new WindowsFileWriter();
    private final int CANCELLATION_TIME_IN_WEEKS = 1;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String placeOrder(String showName, String desiredDateStr, int numberOfTickets) {
        LocalDate desiredDate = parseDate(desiredDateStr);
        LocalDate currentDate = LocalDate.now();
        validateDates(desiredDate);
        LocalDate lastCancellationDate = null;
        if (isMoreThanAWeek(desiredDate, currentDate)) {
            lastCancellationDate = setCancellationDate(currentDate);
        }
        String reportString = generateReportString(showName, desiredDateStr, numberOfTickets, lastCancellationDate);
        windowsFileWriter.write(reportString);
        S3TicketsDao.getInstance().uploadObject(reportString);
        return reportString;

    }

    private LocalDate setCancellationDate(LocalDate currentDate) {
        LocalDate localDate = currentDate.plusWeeks(CANCELLATION_TIME_IN_WEEKS);
        return localDate;
    }

    private String generateReportString(String showName, String desireDate, int numberOfTickets, LocalDate lastCancellationDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order report:");
        sb.append("\n");
        sb.append("Show name: " + showName);
        sb.append("\n");
        sb.append("Desired date: " + desireDate);
        sb.append("\n");
        sb.append("Number of tickets: " + numberOfTickets);
        sb.append("\n");
        sb.append("Last cancellation Date: " + formatDate(lastCancellationDate));
        sb.append("\n");
        return sb.toString();
    }

    private void validateDates(LocalDate desiredDate) {
        if (desiredDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Desired date has already passed");
        }
    }


    private LocalDate parseDate(String desiredDateStr) {
        return LocalDate.parse(desiredDateStr, formatter);
    }

    private String formatDate(LocalDate date) {
        return date!= null ? formatter.format(date) : "None";
    }


    private boolean isMoreThanAWeek(LocalDate desiredDate, LocalDate currentDate) {
        return DAYS.between(currentDate, desiredDate) > 7;
    }
}
