import React, {Component} from 'react';
import Image from 'next/image';
import Col from 'react-bootstrap/Col';

export default class TopBar extends Component {

    render() {

        return (
            <div id="topBar">
                <Image src="/DMDesignLogoSmooth.png" alt="noDMDesignLogoFound" width="200" height="200"/>
                <h1 id="businessDesc">Kompleksowe usługi glazurnicze</h1>
                    <div id="contactDetails">
                        <h3>Kontakt</h3>
                        <div>
                        <Image src="/phoneIcon.png" alt="noPhoneIconFound" width="40" height="40"/>
                        <p>+48 791 821 500</p>
                        </div>
                        <div>
                        <Image src="/facebookIcon.png" alt="noFBIconFound" width="40" height="40"/>
                        <a href="https://www.facebook.com/DM-Design-1500223413328988/">DM Design</a>
                        </div>
                    </div>
            </div> )

    }

}