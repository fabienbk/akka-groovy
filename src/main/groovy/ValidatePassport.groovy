import actor.msg.CallSmartEyeResult
import actor.msg.CallVelocityCheckResult
import api.ServiceAPI
import api.Service
import pojo.Document
import pojo.Identity

/**
 * This script runs a document validation workflow against an identity
 */
def main(ServiceAPI api, Identity identity, Document document) {

    println("Running script for identity $identity.id")

    resultAsync = null

    api.callAsync(Service.VELOCITY_CHECK, { CallVelocityCheckResult result ->
        println("velocity result : " + result.getVelocityStatus())
        resultAsync = result
    })

    CallSmartEyeResult resultSync = api.call(Service.SMARTEYE)

    doc = resultSync.getDocument()

    api.waitForCompletion()

    println("results= $resultAsync and $resultSync")

}