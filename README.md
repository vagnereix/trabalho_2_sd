# Relatório – Trabalho 2: Comunicação Remota via RMI com Protocolo JSON

> [!NOTE]
>
> 🎉 [Vídeo demo](https://youtu.be/clFqSAWNs9g)

## 1. Objetivo

Este trabalho consistiu em evoluir a implementação do Trabalho 1 (sistema de biblioteca) para um ambiente distribuído, utilizando a comunicação cliente-servidor baseada em RMI e um protocolo customizado de requisição-resposta. O protocolo empacota mensagens em JSON contendo os campos: _messageType_, _requestId_, _objectReference_, _methodId_ e _arguments_. Além disso, os métodos obrigatórios, como **doOperation**, **getRequest** e **sendReply**, foram implementados.

## 2. Implementação

### 2.1. Camada de Domínio

- **Classes POJO:** Foram implementadas as classes _Author_, _Item_, _Book_, _Magazine_ e _Library_.
- **Composição e Herança:**
  - _Book_ e _Magazine_ estendem _Item_ (relação “é-um”).
  - _Library_ agrega listas de _Book_ e _Magazine_ (relação “tem-um”).
- **Serialização:** Todas as classes que são passadas por valor implementam `Serializable`.

### 2.2. Protocolo de Comunicação

- **Formato JSON:**
  - **RequestMessage:** Contém _messageType_, _requestId_, _objectReference_, _methodId_ e _arguments_ (em JSON).
  - **ReplyMessage:** Contém _messageType_, _requestId_ e _returnValue_ (em JSON).
- **Utilitário JsonUtils:** Utiliza a biblioteca Gson para serializar e desserializar os objetos, garantindo a passagem por valor conforme os requisitos.

### 2.3. Serviço Remoto e Métodos Obrigatórios

- **Interface RemoteInvocation:** Define o método `doOperation(String objectReference, String methodId, byte[] arguments)`.
- **Implementação RemoteInvocationImpl:**
  - Exposta via RMI (extendendo _UnicastRemoteObject_) para comunicação real, utilizando IP e porta.
  - O método **doOperation** monta o _RequestMessage_ e o encaminha ao método **getRequest**, que processa o despacho dos métodos com base no _methodId_.
  - Após a execução, o método **sendReply** empacota o _ReplyMessage_ (contendo o valor de retorno) em JSON e o envia de volta ao cliente.
- **Observação:** A alteração realizada em _RemoteInvocationImpl_ – em que o método **doOperation** envia o _RequestMessage_ para o **getRequest** que retorna o valor após o despacho – está de acordo com os requisitos, pois garante o fluxo completo da requisição e resposta conforme solicitado.

### 2.4. Camada Cliente e Comunicação

- **RequestReplyProtocol:** Responsável por empacotar as chamadas, invocar o método remoto **doOperation** via RMI e retornar os bytes da resposta.
- **LibraryStub:** Implementa a interface _LibraryRemote_, realizando a tradução dos métodos de negócio para chamadas remotas utilizando o protocolo customizado (monta as requisições em JSON e processa as respostas).
- **Cliente e Servidor Reais:**
  - **ServerMain:** Cria o registro RMI (em uma porta real, ex.: 1099) e realiza o _bind_ do objeto remoto.
  - **ClientMain:** Faz o _lookup_ do serviço remoto utilizando o IP/porta reais e invoca os métodos expostos (como _addBook_, _addMagazine_, _listBooks_ e _listMagazines_).

## 3. Conformidade com os Requisitos

- **Mínimo de 4 POJOs:** Foram implementadas as classes _Author_, _Item_, _Book_, _Magazine_ e _Library_.
- **Composição “tem-um” e Extensão “é-um”:**
  - _Library_ agrega _Book_ e _Magazine_;
  - _Book_ e _Magazine_ estendem _Item_.
- **4 Métodos para Invocação Remota:** Os métodos _addBook_, _addMagazine_, _listBooks_ e _listMagazines_ atendem ao requisito.
- **Passagem por Valor e Referência:**
  - Objetos de modelo são passados por valor (serializados via JSON).
  - O objeto remoto é acessado por referência através do stub RMI.
- **Protocolos Obrigatórios Implementados:**
  - _doOperation_, _getRequest_ e _sendReply_ foram implementados conforme as especificações, garantindo o empacotamento e a troca de mensagens em JSON.

## 4. Conclusão

A solução implementada cumpre todos os requisitos do trabalho:

- **Comunicação distribuída real** via RMI, utilizando IP e porta.
- **Protocolo customizado** para requisição-resposta com mensagens empacotadas em JSON.
- Implementação dos métodos obrigatórios e estrutura de classes (POJOs, herança e agregação) conforme exigido.
