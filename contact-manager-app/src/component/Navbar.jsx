import SearchContact from "./contact/SearchContact";

import {BACKGROUND, PURPLE} from "../constant/Color.jsx";
import Messages from "../constant/local/Messages.jsx";

const Navbar = () => {
    return (
        <nav
            className="navbar navbar-dark navbar-expand-sm shadow-lg"
            style={{backgroundColor: BACKGROUND}}>
            <div className="container">
                <div className="row w-100">
                    <div className="col">
                        <div className="navbar-brand">
                            <i className="fas fa-id-badge" style={{color: PURPLE}}/>{" "}
                            {Messages.fa.messages['pwa-management']}{"  "}
                            <span style={{color: PURPLE}}>{Messages.fa.messages['contact']}</span>
                        </div>
                    </div>
                    <div className="col">
                        <SearchContact/>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
