package rem

class DateTagLib {
    
    static namespace ="rem"
    
    def thisYear = {
        out << Calendar.getInstance().get(Calendar.YEAR)
    }
    
    def copyright ={ attrs, body ->
        out << "&copy; ${attrs['startYear']} - ${thisYear()}, ${body()}"
        out << " All Rights Reserverd."
    }

}
