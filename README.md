## Projecto de Engenharia de Software II

# Notas importantes sobre o setup do projecto:
<ul>
    <li>Versão do SpringBoot: 3.1.7</li>
    <li>Versão do java: 17 ou acima</li>
    <li>
        Dependencias: https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.7&packaging=jar&jvmVersion=17&groupId=com.bluehouse&artifactId=bluehouse&name=bluehouse&description=This%20is%20a%20police%20squad%20management%20system&packageName=com.bluehouse.bluehouse&dependencies=data-jpa,postgresql,web,web,devtools,lombok,thymeleaf
    </li>
    <li>
        Dados do Banco de Dados no arquivo: src\main\resources\application.properties
        <ul>
            <li> Obrigatorio criar um banco de dados no postgres com o  nome: db_blue_house</li>
            <li> username e password: os da vossa máquina</li>
            <li>Ao rodarem o projecto pela primeira vez, devem cadastrar no postgres um funcionario admin</li>
            <li>Na tabela funcionário tem um atributo chamado role, este é do tipo enum e armazena "admin or user". Porem para vocês conseguirem ter acesso a todas urls devem se cadastrar como admin.</li>
            <li>A senha cadastrada na bd deve ser encriptada, acessem ao site "https://bcrypt-generator.com/" para encriptarem as vossas senhas. Esse ponto é muito importante para que vocês possam aceder ao sistema, caso contrário é pau</li>

</ul> 

# Nota: 
### Se estiver usando o  VS Code, e ele não estiver rodando a aplicação, pode ser pela ausência dos arquivos classes na pasta target, neste caso, recomendamos a tentar executar a aplicação numa IDE mais robusta como o IntelliJ, que cria todos os arquivos automaticamente nessas situações
