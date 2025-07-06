
// İsim: Rabee Sultan
// Öğrenci No: b121210080
// Ödev: Uzay Simülasyonu
package firstProgram;

import java.time.LocalDate;

public class Time {
    private int hoursPerDay;          // عدد الساعات في اليوم (خاص بالكوكب)
    private int totalHoursPassed;     // كم ساعة مرت منذ البداية
    private LocalDate startDate;      // التاريخ الابتدائي للكوكب

    public Time(int hoursPerDay, LocalDate startDate) {
        this.hoursPerDay = hoursPerDay;
        this.startDate = startDate;
        this.totalHoursPassed = 0; // يبدأ من الصفر
    }

    // إضافة عدد معين من الساعات
    public void advanceTime(int hours) {
        this.totalHoursPassed += hours;
    }

    // التقدم بساعة واحدة
    public void advanceOneHour() {
        advanceTime(1);
    }

    // الحصول على التاريخ الحالي للكوكب
    public LocalDate getCurrentDate() {
        int totalDaysPassed = totalHoursPassed / hoursPerDay;
        return startDate.plusDays(totalDaysPassed);
    }

    // الحصول على الساعة الحالية في اليوم
    public int getCurrentHour() {
        return totalHoursPassed % hoursPerDay;
    }

    // صيغة عرض الوقت الحالي
    @Override
    public String toString() {
        return String.format("%s %02d:00", getCurrentDate(), getCurrentHour());
    }
}
