package platform;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class CodeSharingPlatformController {

    @Autowired
    LoadDate date;

    private Code cd = new Code();

    // API endpoint for posting code snippet
    @PostMapping("/api/code/new")
    public ResponseEntity postCode(@RequestBody Code rqstCode) {
        cd.setCode(rqstCode.getCode());
        cd.setDate(LoadDate.getLoadDate());
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();

        return responseBuilder.body(new EmptyJsonResponse());
    }

    // API endpoint for retrieving code snippet
    @GetMapping(value = "/api/code", produces = "application/json")
    public ResponseEntity<Code> getCodeSnippet() {
        return ResponseEntity.ok(cd);
    }

    // Browser endpoint for posting code snippet
    @GetMapping(value = "/code/new", produces = "text/html")
    public ModelAndView createCodeSnippet() {
        return new ModelAndView("create");
    }

    // Browser endpoint for retrieving code snippet
    @GetMapping(value = "/code", produces = "text/html")
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("time", cd.getDate());
        mv.addObject("code", cd.getCode());
        return mv;
    }
}
