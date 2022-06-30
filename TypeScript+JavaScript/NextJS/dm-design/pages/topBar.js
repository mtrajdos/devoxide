import React, {Component} from 'react';
import Image from 'next/image';

export default class TopBar extends Component {

    constructor(props) {
        super(props);
    }

    facebookURL = "https://www.facebook.com/DM-Design-1500223413328988/";

    render() {

        return (
            <div id="topBar">
                <Image src="/DMDesignLogoSmooth.png" alt="noDMDesignLogoFound" width="200" height="200"/>
                <h1 id="businessDesc">Kompleksowe usługi glazurnicze</h1>
                    
                <div id="contactDetails">
                            <div className="contactTile">
                                <Image src="/facebookIcon.png" alt="noFBIconFound" width="40" height="40"/>
                                <a href={this.facebookURL}>DM Design</a>
                            </div>

                            <div className="contactTile">
                                <Image src="/phoneIcon.png" alt="noPhoneIconFound" width="40" height="40"/>
                                <span>791 821 500</span>
                            </div>
                    </div>
            </div> )

    }

}