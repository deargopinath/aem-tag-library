<%@include file="/apps/aemtaglibs/global.jsp"%>
<%@include file="/apps/aemtaglibs/aemTagLibrary.jsp"%>

<slingx:property var="heading" name="heading" resourcePath="."/>
<slingx:property var="customer" name="customerName" resourcePath="."/>
<slingx:property var="addr" name="address" resourcePath="."/>
<slingx:property var="zip" name="zip" resourcePath="."/>
<slingx:property var="phone" name="phone" resourcePath="."/>
<slingx:property var="email" name="email" resourcePath="."/>
<slingx:property var="account" name="account" resourcePath="."/>

<div class="fls-inner-block" style="border:1px solid black">
    <div class="fls-inner-content">
        <h3><atl:write value='${heading}' defaultValue='- Heading here -' editMode='${isEditMode}'/></h3>
        <div class="grid">
            <div class="col-1">
				<atl:write value='${customer}' defaultValue='- Customer name -' editMode='${isEditMode}'/><br>
                <atl:write value='Address: ${addr}' defaultValue='- Address here -' editMode='${isEditMode}'/><br>
                <atl:write value='Postal code: ${zip}' defaultValue='- ZIP Label here -' editMode='${isEditMode}'/><br>
                <atl:write value='Phone# ${phone}' defaultValue='- Phone number -' editMode='${isEditMode}'/><br>
                <atl:write value='Email: ${email}' defaultValue='- Email address -' editMode='${isEditMode}'/><br>
            </div>
        </div>
    </div>
</div>
