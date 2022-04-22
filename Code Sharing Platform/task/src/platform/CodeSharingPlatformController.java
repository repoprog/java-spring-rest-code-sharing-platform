package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CodeSharingPlatformController {

    @Autowired
    private LoadDate date;

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeService codeService;


    // API endpoint for posting code snippet
    @PostMapping(value = "/api/code/new", produces = "application/json")
    public ResponseEntity<?> postCodeSnippet(@RequestBody Code rqstCode) {
        rqstCode.setDate(LoadDate.getLoadDate());
        codeService.saveCode(rqstCode);
        return ResponseEntity.ok(new DTOCode(String.valueOf(rqstCode.getId())));
    }

    // API endpoint for retrieving code snippet
    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public ResponseEntity<Code> getCodeSnippet(@PathVariable Long id) {
        Code codeByID = codeService.findCodeById(id);
        return ResponseEntity.ok(codeByID);
    }

    // Browser endpoint for posting code snippet
    @GetMapping(value = "/code/new", produces = "text/html")
    public ModelAndView createCodeSnippet() {
        return new ModelAndView("create");
    }

    // Browser endpoint for retrieving code snippet
    @GetMapping(value = "/code/{id}", produces = "text/html")
    public ModelAndView getIndex(@PathVariable Long id) {
        Code codeByID = codeService.findCodeById(id);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("time", codeByID.getDate());
        mv.addObject("code", codeByID.getCode());
        return mv;
    }

    // API endpoint for retrieving 10 latest code codeSnippet
    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public ResponseEntity<List<Code>> getLatestCodeSnippet() {
        List<Code> sortedList = codeService.findLatest10CodesSorted();
        return ResponseEntity.ok(sortedList);
    }

    // Browser endpoint for retrieving 10 Latest codeSnippet
    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatest(Code code) {
        List<Code> sortedList = codeService.findLatest10CodesSorted();
        ModelAndView mv = new ModelAndView("latest");
        mv.addObject("codes", sortedList);
        return mv;
    }


}