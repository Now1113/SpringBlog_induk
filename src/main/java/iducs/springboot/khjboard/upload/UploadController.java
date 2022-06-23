package iducs.springboot.khjboard.upload;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
public class UploadController {

    @ResponseBody
    @GetMapping("/files/{filepath}")
    public Resource downloadImage(@PathVariable String filepath) throws MalformedURLException {
        return new UrlResource("file:"  + "C:\\springfinal\\201812058\\khj-board\\khj-board\\src\\main\\resources\\static\\files\\" + filepath);
    }


}
