import React from 'react';
import './global.css';
import './App.css';
import './SideBar.css'
import './Main.css'
function App() {
  return (
    <div id="app">
      <aside>
        <strong>Cadastrar</strong>
        <form>
          <div class="input-block">
            <label htmlFor="github">Github</label>
            <input name="github" id="github" />
          </div>
          <div class="input-block">
            <label htmlFor="techs">Tecnologias</label>
            <input name="techs" id="techs" />
          </div>

          <div className="input-group">
            <div class="input-block">
              <label htmlFor="latitude">Latitude</label>
              <input name="latitude" id="latitude" />
            </div>

            <div class="input-block">
              <label htmlFor="longitude">Longitude</label>
              <input name="longitude" id="longitude" />
            </div>

          </div>
          <button type="submit">Salvar</button>
        </form>
      </aside>
      <main>
        <ul>
        <li className="dev-item">
              <header>
                <img src="https://avatars1.githubusercontent.com/u/9970061?s=460&v=4" alt="Franklim"/>
                <div className="user-info">
                  <strong>Paulo Franklim</strong>
                  <span>Java, Node.js</span>
                  <p>Biografia</p>
                  <a href="https://github.com/franklim">Acessar perfil no Github</a>
                </div>
              </header>
          </li>
          <li className="dev-item">
              <header>
                <img src="https://avatars1.githubusercontent.com/u/9970061?s=460&v=4" alt="Franklim"/>
                <div className="user-info">
                  <strong>Paulo Franklim</strong>
                  <span>Java, Node.js</span>
                  <p>Biografia</p>
                  <a href="https://github.com/franklim">Acessar perfil no Github</a>
                </div>
              </header>
          </li>
          <li className="dev-item">
              <header>
                <img src="https://avatars1.githubusercontent.com/u/9970061?s=460&v=4" alt="Franklim"/>
                <div className="user-info">
                  <strong>Paulo Franklim</strong>
                  <span>Java, Node.js</span>
                  <p>Biografia</p>
                  <a href="https://github.com/franklim">Acessar perfil no Github</a>
                </div>
              </header>
          </li>
          <li className="dev-item">
              <header>
                <img src="https://avatars1.githubusercontent.com/u/9970061?s=460&v=4" alt="Franklim"/>
                <div className="user-info">
                  <strong>Paulo Franklim</strong>
                  <span>Java, Node.js</span>
                  <p>Biografia</p>
                  <a href="https://github.com/franklim">Acessar perfil no Github</a>
                </div>
              </header>
          </li>
        </ul>
      </main>
    </div>
  );
}

export default App;
