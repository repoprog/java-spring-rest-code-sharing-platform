package platform;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

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
        rqstCode.setUuid(UUID.randomUUID().toString());
        codeService.saveCode(rqstCode);
        return ResponseEntity.ok(new UUIDResponse(rqstCode.getUuid()));
    }

    // API endpoint for retrieving code snippet
    @GetMapping(value = "/api/code/{uuid}", produces = "application/json")
    public ResponseEntity<?> getCodeSnippet(@PathVariable String uuid) {
        Code codeByID = codeService.findCodeByUUID(uuid);
        Code codePermitted = codeService.checkRestrictions(codeByID);
        //DTO for practice used only in API response to hide field restricted in API
        // could be done by @JsonIgnore above restricted field
        CodeDto codeDto = new CodeDto();
        BeanUtils.copyProperties(codePermitted, codeDto);
        return ResponseEntity.ok(codeDto);
    }

    // Browser endpoint for posting code snippet
    @GetMapping(value = "/code/new", produces = "text/html")
    public ModelAndView createCodeSnippet() {
        return new ModelAndView("create");
    }

    // Browser endpoint for retrieving code snippet
    @GetMapping(value = "/code/{uuid}", produces = "text/html")
    public ModelAndView getIndex(@PathVariable String uuid) {
        Code codeByID = codeService.findCodeByUUID(uuid);
        Code codePermitted = codeService.checkRestrictions(codeByID);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("codeInstance", codePermitted);
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