# SpringBatch eräajo

Eräajo on toteutettu osana Haaga-Helian tutkimusprosessi-kurssia ja sen tavoitteena on tutkia Spring Batch transaktiokäsittelyä paloittain prosessoitavalla eräajolla.

Toteutuksen vaiheet ovat omaissa brancheissaan
* 1_noError - onnistunut eräajo, joka prosessoi 31 henkilön tietoa 10 kappaleen paloina ja tallentaa tiedot muistinvaraiseen H2 tietokantaan
* 2_processorError
* 3_writerError
* 4_retryWithProcessorError
* 5_retryWithWriterError
* 6_skipWithProcessorError
* 7_skipWithWriterError
