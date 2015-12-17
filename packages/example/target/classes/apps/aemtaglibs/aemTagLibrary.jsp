<%-- global.jsp - Include only if it is not included anywhere else --%>
<%--
    <%@include file="/libs/foundation/global.jsp"%>
--%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page import="com.day.cq.wcm.api.WCMMode"%>
<%-- Standard tags - Include only if these are not included anywhere else--%>
<%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0"%>
<%--
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%>
    <%@taglib prefix="wcmmode" uri="http://www.adobe.com/consulting/acs-aem-commons/wcmmode"%>
--%>
<%-- Dependencies for AEM Tag Library --%>
<%@taglib prefix="ss" uri="http://www.squeakysand.com/taglibs/ss"%>
<%@taglib prefix="jcrx" uri="http://www.squeakysand.com/taglibs/jcrx"%>
<%@taglib prefix="slingx" uri="http://www.squeakysand.com/taglibs/slingx"%>
<%-- Begin:  AEM Tag Library --%>
<%@taglib prefix="atl" uri="http://developer.aemtaglibs.com/taglibs"%>
<%-- Page modes --%>
<% WCMMode wcmMode = WCMMode.fromRequest(request); %>
<c:set var="isEditMode" value="<%= wcmMode.equals(WCMMode.EDIT) %>"/>
<c:set var="isDesignMode" value="<%= wcmMode.equals(WCMMode.DESIGN) %>"/>
<c:set var="isPreviewMode" value="<%= wcmMode.equals(WCMMode.PREVIEW) %>"/>
<c:set var="isReadOnlyMode" value="<%= wcmMode.equals(WCMMode.READ_ONLY) %>"/>
<c:set var="isWCMDisabled" value="<%= wcmMode.equals(WCMMode.DISABLED) %>"/>
<%-- Paths --%>
<c:set var="resourcesPath" value="<%= currentDesign.getPath()%>" />
<c:set var="projectPath" value="<%=currentPage.getAbsoluteParent(2).getPath()%>"/>
<c:set var="currentPagePath" value="<%=currentPage.getPath()%>"/>
<c:set var="isExternalContent" value="${fn:containsIgnoreCase(currentPagePath, 'tools/external/')}"/>
<%-- hostname, scheme, query, uri, full-url  --%>
<c:set var="serverName" value="<%= slingRequest.getServerName() %>"/>
<c:set var="scheme" value="<%= slingRequest.getScheme() %>"/>
<c:set var="queryString" value="<%= slingRequest.getQueryString() %>"/>
<c:set var="uri" value="<%= slingRequest.getRequestURI().toString() %>"/>
<c:choose>
    <c:when test="${not empty queryString}">
        <c:set var="fullPageUrl" value="${scheme}://${serverName}${uri}?${queryString}"/>
    </c:when>
    <c:otherwise>
        <c:set var="fullPageUrl" value="${scheme}://${serverName}${uri}"/>
    </c:otherwise>
</c:choose>
<%-- End: AEM Tag Library --%>
