import React from 'react';
import './style.css';

function DevItem(props) {
    const { dev } = props;
    return (
        <li className="dev-item">
            <header>
                <img src={dev.avatar} alt={dev.name} />
                <div className="user-info">
                    <strong>{dev.github}</strong>
                    <span>{dev.techs}</span>
                    <p>{dev.bio}</p>
                    <a href={"https://github.com/" + dev.github}>Acessar perfil no Github</a>
                </div>
            </header>
        </li>
    )
}

export default DevItem;