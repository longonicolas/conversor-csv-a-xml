package csvtoxml.services;

import csvtoxml.entities.Row;
import csvtoxml.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateRowListService {

    private final RowRepository rowRepository;

    @Autowired
    ToXmlService toXmlService;

    @Autowired
    public GenerateRowListService(RowRepository rowRepository,ToXmlService toXmlService) {
        this.rowRepository = rowRepository;
        this.toXmlService = toXmlService;
    }

    public List<Row> getAllRows() {
        toXmlService.createDocument(rowRepository.findAll());
        return rowRepository.findAll();

    }
}