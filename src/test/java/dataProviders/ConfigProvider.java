package dataProviders;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dto.color.ColorDTO;

public class ConfigProvider {

    public static Config readConfig() {
        return ConfigFactory.load("application.conf");
    }

    public static ColorDTO CERULEAN = ColorDTO.builder()
            .id(readConfig().getInt("colors.cerulean.id"))
            .name(readConfig().getString("colors.cerulean.name"))
            .year(readConfig().getInt("colors.cerulean.year"))
            .color(readConfig().getString("colors.cerulean.color"))
            .pantoneValue(readConfig().getString("colors.cerulean.pantone_value"))
            .build();
    public static ColorDTO FUCHSIA_ROSE = ColorDTO.builder()
            .id(readConfig().getInt("colors.fuchsiaRose.id"))
            .name(readConfig().getString("colors.fuchsiaRose.name"))
            .year(readConfig().getInt("colors.fuchsiaRose.year"))
            .color(readConfig().getString("colors.fuchsiaRose.color"))
            .pantoneValue(readConfig().getString("colors.fuchsiaRose.pantone_value"))
            .build();
    public static ColorDTO TRUE_RED = ColorDTO.builder()
            .id(readConfig().getInt("colors.trueRed.id"))
            .name(readConfig().getString("colors.trueRed.name"))
            .year(readConfig().getInt("colors.trueRed.year"))
            .color(readConfig().getString("colors.trueRed.color"))
            .pantoneValue(readConfig().getString("colors.trueRed.pantone_value"))
            .build();


}
