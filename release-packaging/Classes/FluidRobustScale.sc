FluidRobustScale : FluidRealTimeModel {

    var <>low, <>high, <>invert;

	*new {|server, low = 25, high = 75, invert = 0|
		^super.new(server,[low,high,invert])
		.low_(low).high_(high).invert_(invert);
	}

    prGetParams{
        ^[this.low,this.high,this.invert,-1,-1];
    }


    fitMsg{|dataSet|
        ^this.prMakeMsg(\fit,id,dataSet.id)
    }

	fit{|dataSet, action|
        actions[\fit] = [nil,action];
		this.prSendMsg(this.fitMsg(dataSet));
	}

    transformMsg{|sourceDataSet, destDataSet|
        ^this.prMakeMsg(\transform,id,sourceDataSet.id,destDataSet.id);
    }

	transform{|sourceDataSet, destDataSet, action|
		actions[\transform] = [nil,action];
        this.prSendMsg(this.transformMsg(sourceDataSet, destDataSet));
	}

    fitTransformMsg{|sourceDataSet, destDataSet|
        ^this.prMakeMsg(\fitTransform,id,sourceDataSet.id,destDataSet.id)
    }

	fitTransform{|sourceDataSet, destDataSet, action|
        actions[\fitTransform] = [nil,action];
		this.prSendMsg(this.fitTransformMsg(sourceDataSet, destDataSet));
	}

    transformPointMsg{|sourceBuffer, destBuffer|
        ^this.prMakeMsg(\transformPoint,id,
            this.prEncodeBuffer(sourceBuffer),
            this.prEncodeBuffer(destBuffer),
            ["/b_query",destBuffer.asUGenInput]
        );
    }

	transformPoint{|sourceBuffer, destBuffer, action|
        actions[\transformPoint] = [nil,{action.value(destBuffer)}];
        this.prSendMsg(this.transformPointMsg(sourceBuffer, destBuffer));
	}

    kr{|trig, inputBuffer,outputBuffer,low,high,invert|

        low = low ? this.low;
        high = high ? this.high;
		invert = invert ? this.invert;

        this.low_(low).high_(high).invert_(invert);

        ^FluidRobustScaleQuery.kr(K2A.ar(trig),this, this.low, this.high, this.invert, this.prEncodeBuffer(inputBuffer), this.prEncodeBuffer(outputBuffer));
    }


}

FluidRobustScaleQuery : FluidRTQuery {}
