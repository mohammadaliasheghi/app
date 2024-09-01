import {useState} from "react";

import {Contacts, Navbar,} from "./component";
import "./App.css";

const App = () => {
    const [loading] = useState(false);
    const [getContacts] = useState([]);

    return (
        <div className="App">
            <Navbar/>
            <Contacts contacts={getContacts} loading={loading}/>
        </div>
    );
};

export default App;