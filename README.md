# logevents
Tanımlanan model sınıfları EventClass interfaceni implement etmeli ve method bodysi sağlanmalıdır. Method bodysinin örneği için customer 
modeline bakılabilir. Oluşturulan her bir model için bir event sınıfı ve event repository sınıfı oluşturulmadır. Event sınıfları AbstractEvent abstract sınıfından extend olmalıdır. 

Customer nesnesini kaydederken ve silerken event kayıtları atılmaktadır. Repository'de bulunan save, saveall, delete,deleteall methodları kapsanmaktadır.
