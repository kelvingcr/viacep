<h1 align="center">ViaCEP</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <br>
  <a href="https://wa.me/+5513988841491"><img alt="WhatsApp" src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/kelvingcr/"><img alt="Linkedin" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="mailto:kelvingcr16@gmail.com"><img alt="Gmail" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>
</p>

<p align="center">  

⭐ Esse é um projeto para demonstrar meu conhecimento técnico no desenvolvimento Android nativo com Kotlin. Mais informações técnicas abaixo.

🗺 <b> VIACEP </b> é um aplicativo que permite aos usuários consultar endereços a partir de um CEP (Código de Endereçamento Postal). Com base no CEP fornecido pelo usuário, o aplicativo acessa um banco de dados atualizado com informações de endereçamento de todo o Brasil e fornece o endereço completo, incluindo rua, bairro, cidade e estado. Isso torna mais fácil e rápido para os usuários encontrarem informações precisas sobre endereços, facilitando, por exemplo, a entrega de mercadorias ou a localização de locais específicos. O aplicativo é gratuito e está disponível em plataformas móveis. (não disponivel na playstore.)

</p>

</br>

<p float="left" align="center">
  <img alt="screenshot" width="100%" src="https://i.imgur.com/4pUVAL7.png"/>
</p>

## Download

Faça o download da <a href="link aqui download">APK diretamente</a>. Você pode ver <a href="https://www.google.com/search?q=como+instalar+um+apk+no+android">aqui</a> como instalar uma APK no seu aparelho android.

## Tecnologias usadas e bibliotecas de código aberto

- Minimum SDK level 28
- [Linguagem Kotlin](https://kotlinlang.org/)

- Jetpack
  - Lifecycle: Observe os ciclos de vida do Android e manipule os estados da interface do usuário após as alterações do ciclo de vida.
  - ViewModel: Gerencia o detentor de dados relacionados à interface do usuário e o ciclo de vida. Permite que os dados sobrevivam a alterações de configuração, como rotações de tela.
  - ViewBinding: Liga os componentes do XML no Kotlin através de uma classe que garante segurança de tipo e outras vantagens.
  - Custom Views: View customizadas feitas do zero usando XML.
  - Navigation: Uma biblioteca que ajuda a gerenciar a navegação em um aplicativo. Ele oferece uma API intuitiva para criar e gerenciar fluxos de navegação entre diferentes telas e fragmentos.
  - LiveData: Um objeto observável que armazena um valor de dados e notifica automaticamente as observadoras quando há mudanças nos dados.
  - Flow: é uma API de programação reativa que permite a criação de sequências de valores assíncronas, que podem ser transformadas e observadas por outros objetos. 
  
- Arquitetura
  - Clean Architecture
  - MVVM (View - ViewModel - Model)
  - Comunicação da ViewModel com a View através de LiveData
  - Comunicação da ViewModel com a Model através de Kotlin Flow
  - Repositories para abstração da comunidação com a camada de dados.
  
- Bibliotecas
  - [Hilt](https://github.com/googlecodelabs/android-hilt): Para injeção de dependencias automaticas.
  - [Retrofit & OkHttp](https://square.github.io/retrofit/): Para carregamento de imagens e cacheamento das mesmas.
  - [Room DataBase](https://developer.android.com/jetpack/androidx/releases/room): Para carregamento de imagens e cacheamento das mesmas.

## Arquitetura
**ViaCEP** utiliza a arquitetura MVVM e o padrão de projetos Clean Architecture, que segue as [recomendações oficiais do Google](https://developer.android.com/topic/architecture).
</br></br>
<img width="50%" src="https://i.imgur.com/336KI4q.png"/>
<br>

<img width="100%" src="https://i.imgur.com/zW4Bpcu.png"/>

## Features

### Tela de Listagem de endereços;

<p float="left" align="start">
  <img src="https://i.imgur.com/91tDUiw.jpg" width="25%"/>
</p>

Nessa tela irão ser listadas todos os endereços salvos pelo usuário.
Ainda nessa tela o usuário poderá navegar ao endereço clicando no icone azul de rota.

### Tela de salvamento de endereço

<p float="left" align="start">
  <img src="https://i.imgur.com/ihl9Zrk.png" width="25%"/>
  <img src="https://i.imgur.com/7C6YaVW.png" width="25%"/>
</p>

Nessa tela o usuário poderá salvar endereços utilizando o cep que eles inserirem.

### Preference Screen

<p float="left" align="start">
  <img src="https://i.imgur.com/7bAXlqt.jpg" width="25%"/>

</p>

# Licença
```xml

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

Google Play e o logótipo do Google Play são marcas comerciais da Google LLC.
