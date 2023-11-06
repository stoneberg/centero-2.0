package kr.centero.core.common.resolver;

import org.springframework.context.i18n.LocaleContext;

import java.util.Locale;
import java.util.TimeZone;

public class CenteroLocaleContext implements LocaleContext {

    private final Locale locale;
    private final TimeZone timeZone;

    public CenteroLocaleContext(Locale locale, TimeZone timeZone) {
        this.locale = locale;
        this.timeZone = timeZone;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }
}
