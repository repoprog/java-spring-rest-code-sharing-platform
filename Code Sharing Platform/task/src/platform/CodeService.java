package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    public void saveCode(Code code) {
        codeRepository.save(code);
    }

    public Code findCodeById(Long id) {

        return codeRepository.findById(id)
                .orElseThrow(() -> new CodeNotFoundException("id:" + id)
                );
    }

    public List<Code> findLatest10CodesSorted() {
        List<Code> repoCodeList = codeRepository.findAll();

        return repoCodeList.stream()
                .sorted(Comparator.comparing(Code::getId).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

}
