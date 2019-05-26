package pers.sfl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot2之后继承了WebMvcConfigurer类导致之后所有的静态资源都404了.
 *
 * @author Scott  fl_6145@163.com
 * @create 2019-05-25 0:25
 */

@Configuration
@EnableWebMvc
public class MyWebConfig implements WebMvcConfigurer {

    /**
     * SpringBoot 2.x要重写该方法，不然css、js、image 等静态资源路径无法访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
    }
}
