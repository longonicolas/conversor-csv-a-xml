package csvtoxml.config;

import csvtoxml.entities.Row;
import org.springframework.batch.item.ItemProcessor;

public class RowProcessor implements ItemProcessor<Row,Row> {
        @Override
        public Row process(Row row) throws Exception {
            return row;
        }
    }

