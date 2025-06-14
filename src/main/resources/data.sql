INSERT INTO document_extension (extension_name,key) VALUES
('pdf','PDF'),
('doc','DOC'),
('docs','DOCS'),
('jpg','JPG'),
('xls','XLS'),
('xlsx','XLSX'),
('txt','TXT'),
('png','PNG')
ON CONFLICT (extension_name) DO NOTHING;
